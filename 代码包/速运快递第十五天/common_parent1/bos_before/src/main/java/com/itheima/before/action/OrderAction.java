package com.itheima.before.action;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.itheima.crm.Customer;
import com.itheima.customerService.CustomerService;
import com.itheima.domain.Area;
import com.itheima.domain.Order;
import com.itheima.manyService.OrderService;
import com.itheima.utils.action.common.CommonAction;


public class OrderAction extends CommonAction<Order>{
	
	//属性驱动接收前台传来的citypicker地址信息并进行解析
	//发件方的省市区
	private String sendAreaInfo;
	//收件方的省市区
	private String recAreaInfo;

	public void setSendAreaInfo(String sendAreaInfo) {
		this.sendAreaInfo = sendAreaInfo;
	}

	public void setRecAreaInfo(String recAreaInfo) {
		this.recAreaInfo = recAreaInfo;
	}
	
	//将收件方和发件方的省市区解析成数组并返回数组
	private String[] cut(String str){
		return str.split("/");
	} 

	//模型驱动接收前台页面传来的表单
	//private Order order;
	//注意，CommonAction类已经封装了model模型驱动，因此绝不能再自己写模型驱动，只需要直接用model即可。

	/*public void setOrder(Order order) {
		this.order = order;
	}*/
	
	//注意，在debug模式下可以修改代码，修改之后不重新运行就可以debug。很神奇。
	
	
	@Autowired
	private OrderService orderService;
	@Autowired
	private CustomerService customerService;
	
	//接收前台传来的密码
	private String password;
	
	public void setPassword(String password) {
		this.password = password;
	}

	//该方法用于用户登录
	@Action(value="customerAction_login",results={@Result(name="error",type="redirect",location="signup-fail.html"),@Result(name="success",type="redirect",location="signup-success.html")})
	public String login(){
		
		//前台传来的用户手机号用模型驱动接收
		String telephone = model.getTelephone();
		//根据手机号和密码来查询数据库中是否存在该用户，假如存在就登陆成功并且写入session
		Customer customer=customerService.findByTelephoneAndPassword(telephone,password);
		System.out.println(customer);
		if(customer!=null){
			ServletActionContext.getRequest().getSession().setAttribute("customer", customer);
			System.out.println("成功存入session");
			return SUCCESS;
		}else {
			return ERROR;
		}
	}
		
	
	
	@Action("orderAction_add")
	public String orderAction_add(){
		
		//注意不要新创建一个订单对象，而是要接着用上面的模型驱动得到的订单对象
		//Order order=new Order();
		//将发件方切割成省市区
		String[] send = this.cut(sendAreaInfo);
		//分别给省市区赋属性值(注意要新建一个Area对象并且传入省市区而不能用order.getArea，因为order对象也是新建的，area属性为null，而不是一个Area对象)
		Area area1=new Area(send[0],send[1],send[2]);
		//将封装好的对象传进去
		model.setSendArea(area1);
		//将收件方切割成省市区
		String[] rec = this.cut(recAreaInfo);
		//分别给省市区赋属性值
		Area area2=new Area(rec[0],rec[1],rec[2]);
		//将封装好的对象传进去
		model.setRecArea(area2);
		
		//订单Order设置完毕了，下面开始设置工单WorkBill并且寻找快递员(寻找快递员是第九天的主要业务逻辑)
		//寻找快递员的方式是寻找定区，然后从一个定区对应的多个快递员里面选一个合适的快递员。
		//寻找定区的方式是根据用户填写的详细地址在customer表中寻找地址与他相等的customer对象，
		//进而找到对应的定区id，进而找到定区对象，进而找到定区对象中的快递员集合
		
		//假如无法找到与用户填写的详细地址地址相等的customer对象，就要用主要关键字和辅助关键字对比用户填写的详细地址(包含关系contains)
		//SubArea表中有主要关键字和次要关键字，该表描述的是分区，注意和区域表Area区分开
		//有了分区就有了定区，因为多个分区对应一个定区，也就是每个分区的定区都是固定的
		
		//必须注意，为订单和工单设置快递员属于业务逻辑，因为他不属于前台直接传来的，需要由分析得出，因此放在service层中，
		//所以也就不用再引入subAreaService服务，因为service层本来就在服务端bos_service中
		
		Customer customer=(Customer) ServletActionContext.getRequest().getSession().getAttribute("customer");
		//假如用户已经登录
		if (customer!=null) {
			model.setCustomer_id(customer.getId());
			model.setTelephone(customer.getTelephone());
		}
		System.out.println(model);
		
		orderService.save(model);
		
		return NONE;
	}
	
	
	
	
}
