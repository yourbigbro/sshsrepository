package com.itheima.before.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import com.aliyuncs.exceptions.ClientException;
import com.itheima.activemq.producer.queue.QueueSender;
import com.itheima.crm.Customer;
import com.itheima.customerService.CustomerService;
import com.itheima.utils.MD5Utils;
import com.itheima.utils.MailUtils;
import com.itheima.utils.SmsUtils;
import com.itheima.utils.action.common.CommonAction;

public class CustomerBeforeAction extends CommonAction<Customer>{
	
	//这里要写注解是因为他是配置文件里面取出来的。它的命名不用非得和application.xml配置文件中bean元素的id的属性值相同，因为他是按照类型注入的
	@Autowired
	RedisTemplate<String, String> redisTemplate=new RedisTemplate<>();
	
	//先引入生产者的工具类才能使用这个变量
	@Autowired
	private QueueSender queueSender;

	/*public static void main(String[] args) {
		String random = RandomStringUtils.randomNumeric(4);
		System.out.println(random);
	}*/
	//该方法用于向客户发送验证码
	@Action("customer_sendMsg")
	public String customer_sendMsg(){
		
		//从模型驱动中取得电话号码变量
		String telephone = model.getTelephone();
		//利用工具类生成四位随机数(注意生成的是字符串而不是数字)
		String random = RandomStringUtils.randomNumeric(4);
		System.out.println("验证码是"+random);
		
		//注意不能初始化成null，否则他就不是hashmap
		HashMap<String, String> map = new HashMap<>();
		map.put("telephone", telephone);
		map.put("content", random);
		//利用工具类将电话号码和随机数验证码发送给客户并将验证码保存在session中
		//注意验证码不能保存在action类中的全局变量中，因为action是多例的
		//用mq从生产端向消费端发送信息(放入消息队列之后就结束了，不用再等到发送短信完毕之后才能结束该方法)
		queueSender.send("mytest.map", map);
		//将下面的具体发送短信的操作放到消费端
		/*try {
			//注意发送验证码会抛出异常
			SmsUtils.sendSms(telephone, random);
			//将验证码保存在session中(注意session从request中获得)
			ServletActionContext.getRequest().getSession().setAttribute(telephone, random);
		} catch (ClientException e) {
			e.printStackTrace();
		}*/
		return NONE;
	}
	
	//提交表单的时候用属性驱动的方式接收验证码
	private String checkcode;
	
	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}
	
	//注意要写注解，因为他在cxf客户端中的application.xml(spring配置文件中)进行了配置
	//区分属性驱动和客户端对象(接口)，前者需要写set方法，后者需要写Autowired注解
	//写Autowired注解不是因为他是引用的其他项目，而是因为该引用的项目是cxf服务端。
	@Autowired
	private CustomerService customerService;

	//该方法用于接受用户提交的表单，进行验证码的校验(和session中的telephone属性作比较)和新用户在数据库内的保存注册
	@Action(value="customer_regist",results={@Result(name="success",type="redirect",location="signup-success.html"),@Result(name="input",type="redirect",location="signup-fail.html")})
	public String customer_regist(){
		
		//当flag=0的时候没通过验证，当flag=1的时候通过验证
		//只要短信和邮箱验证有一个通过就可以注册成功，也就是flag=1
		Integer flag=0;
		
		//注意，从session取出来的属性值要强转
		String code=(String)ServletActionContext.getRequest().getSession().getAttribute("checkcode");
		//注意，先检测验证码是否存在，再比较验证码是否正确(注意检测的是checkcode而不是code,因为后者一定是存在的，前者却不一定存在)
		//注意用StringUtils.isNotBlank(checkcode)而不是checkcode!=null
		if(StringUtils.isNotBlank(checkcode)&&checkcode.equals(code)){
			//验证码正确，就向数据库保存用户信息(暂未保存，因为感觉数据严重不全，不知道怎么保存，毕竟只有电话号码和密码)
			flag=1;
		}
		
		//从模型驱动里面取出前台页面传来的表单元素
		String email = model.getEmail();
		String telephone = model.getTelephone();
		//生成24位激活码
		String ran = RandomStringUtils.random(24);
		//将telephone和激活码存储到redis数据库，有效期为24小时
		redisTemplate.opsForValue().set(telephone, ran, 1,TimeUnit.DAYS );
		//编写完整的url(也就是用户点击之后跳转的位置)
		String url=MailUtils.activeUrl+"?telephone="+telephone+"&activecode="+ran+"&email="+email;
		//根据用户的注册信息编写邮件的内容
		String content="<a href='"+url+"'>点击激活账号邮箱</a>";
		//给用户指定的邮箱发送邮件
		MailUtils.sendMail("邮箱验证邮件", content, email);
		
		//当flag=1的时候转到成功页面(也就是首页),当flag=0的时候回到登录页面
		if(flag==1){
			
			return INPUT;
		}else {
			//将用户信息存储到数据库中(这个只负责创建用户不负责激活(用户邮箱)，激活用户邮箱在该类中的另一个方法中，位于用户点击的链接中)
			Customer model1=model;
			//手动将用户绑定的邮箱去除，使邮箱暂时不被保存到数据库中
			model1.setEmail(null);
			//将前台传来的密码用md5算法(工具类进行加密)
			model1.setPassword(MD5Utils.md5(model.getPassword()));
			customerService.save(model1);
			return SUCCESS;
		}
	}
	
	//属性驱动接收链接中的激活码
	private String activecode;
	
	public void setActivecode(String activecode) {
		this.activecode = activecode;
	}

	//该方法用于在用户点击邮箱中的链接时激活邮箱
	@Action("customerAction_activeMail")
	private void customerAction_activeMail() throws IOException{
		
		//从redis数据库中获取保存一天的激活码与用户传来的激活码进行比较
		String code = redisTemplate.opsForValue().get(model.getTelephone());
		//如果激活码存在并且正确
		if(code!=null&&code.equals(activecode)){
			//在保存之前先检测邮箱是否已经存在，假如已经存在的话说明已经激活了，就不应该再激活一次
			//查询邮箱所对应的Customer对象，如果存在的话就证明已经激活了
			/////////////////////////////////////////////////////这个功能就不写了，搬砖没卵用
			//将email保存到数据库中
			//老师的是将type属性变为1表示已经激活，可是我是直接传入邮箱，所以就要在用户点击的链接中加入用户邮箱这一项。
			//可是这样用户就可以再激活链接中修改邮箱，所以不太好。还是要将type属性设置为1。但是此处就不操作了。
			customerService.setEmail(model.getEmail(), model.getTelephone());
		}else {
			
			//即使在同一页面中也要每次向页面返回信息时都设置编码格式
			ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
			//返回信息比设置编码格式多一个方法
			ServletActionContext.getResponse().getWriter().write("链接错误或者激活码过期");
		}
	}
}
