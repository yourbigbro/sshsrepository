package com.itheima.utils.action.common;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

//注意，包名必须带action(因为web项目引用utils项目之后相当于web项目中也有一个和utils项目中一样的包，而web项目对action所在的包的包名有要求)，但是action不必在最后。
//这个类应该放在utils工具项目中，因为web项目中含有service层，dao层等的jar包，这些jar包需要在sring配置文件applicationContext.xml里面配置spring data jpa(也就是和数据库有关的项目)(不配置的话就会报错)，会非常麻烦。
//并且其他web项目引用这个web项目属于web项目之间互相引用，显得非常奇怪和混乱

//注意要在类名CommonAction后面加泛型<T>
//注解也可以抽取
@Controller
@Scope("prototype")
@ParentPackage("struts-default")
@Namespace("/")
public class CommonAction<T> extends ActionSupport implements ModelDriven<T> {

	//注意，里面的成员变量应该是protected而不是private，否则子类无法访问到父类的成员变量
	protected T model;
	//既然有模型驱动就必须有下面的
	@Override
	//这个方法子类可以自动调用，因为它不是普通的方法，他是模型驱动
	public T getModel() {
		return model;
	}

	//显式声明无参构造函数，并在里面进行成员变量model的初始化。因为他暂时无法确定类型所以无法在外面直接初始化model变量
	//此无参构造方法在子类初始化的时候自动执行
	//这个无参构造方法存在的目的就是为model赋值
	public CommonAction(){
		//获得子类的类型，例如CourierAction
		Class<? extends CommonAction> clazz = this.getClass();
		//获得该子类的父类(也就是CommonAction<T>)
		Type genericSuperclass = clazz.getGenericSuperclass();
		//获得参数化的类型(也就是CommonAction<Courier>)
		//本来父类不能强制转换成子类，因为子类的方法比父类的方法多。但是当父类具有子类特性的时候就可以将父类强制转换成子类
		ParameterizedType parameterizedType=(ParameterizedType)genericSuperclass;
		//获得被继承的父类中的泛型类型(是一个数组，因为有时候尖括号里面可能是键值对等形式)
		Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
		//取得第一个数组元素即可，并且还要强制转换成Class字节码文件的类型
		Class clazz2=(Class)actualTypeArguments[0];
		try {
			//实例化字节码文件并且强制转换成泛型T，用创建出来的对象为model赋值
			model=(T) clazz2.newInstance();
			//看来这行代码可能抛出两个异常
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	//属性驱动，封装前台页面传来的数据以便使用
	//page指的是当前页码，rows指的是每页的行数
	protected Integer page;
	protected Integer rows;
	
	public void setPage(Integer page) {
		this.page = page;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	
	//将java对象(spring data jpa返回的从数据库中查询到的封装好的分页信息)转换成json对象
	public void java2json(Page pageData,String[] excludes){
		//将Page对象中的信息封装到map集合
				Map<String, Object> map=new HashMap<>();
				//这两个键的名称是不能改变的，否则前台页面无法解析。total表示信息的总数量，rows表示当页的信息的集合
				map.put("total", pageData.getTotalElements());
				map.put("rows", pageData.getContent());
				//将不需要转化成json的集合属性去掉，因为集合属性是懒加载，由service层中的session获得，而到了action层之后session已经关闭了，所以无法获得到，会报错
				//也就是去掉了Courier对象中的某些属性(这些属性都是集合)
				JsonConfig jc = new JsonConfig();
				jc.setExcludes(excludes);
				//将map集合变成json格式
				JSONObject json = JSONObject.fromObject(map, jc);
				//将json格式变为字符串
				String jsonString=json.toString();  
				//设置编码并将字符串传递到前台进行解析
				//注意是getResponse而不是getRequest
				ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
				//将最终得到的json形式的字符串传回给前台页面供easyui解析。注意，没有easyui的话是无法解析这些信息的，信息格式不对也是无法解析的，必须是total和rows
				//到前台页面后这些信息会被显示到table里展示符合要求的每一页的信息
				try {
					//回写信息的这一行会抛出异常
					ServletActionContext.getResponse().getWriter().print(jsonString);
				} catch (IOException e) {
					e.printStackTrace();
				}
	}
	
	//方法的重载。他不是重写，因为重载是参数和返回值不同，重写是参数相同
	//该类中只负责增加一行信息，所以没有分页信息，也就不用相应的属性驱动了
		//该方法已经将转换之后的信息回传到了前台页面
		//将list集合转化成json再转化成字符串
		//注意，list里面不能写泛型T，否则类型只能和模型驱动中的相同
		public void java2json(List list,String[] excludes){
					//将不需要转化成json的集合属性去掉，因为集合属性是懒加载，由service层中的session获得，而到了action层之后session已经关闭了，所以无法获得到，会报错
					//也就是去掉了Courier对象中的某些属性(这些属性都是集合)
					JsonConfig jc = new JsonConfig();
					jc.setExcludes(excludes);
					//将list集合变成json格式
					//将原来的JSONObject变成了JSONArray
					JSONArray json = JSONArray.fromObject(list, jc);
					//将json格式变为字符串
					String jsonString=json.toString();  
					//设置编码并将字符串传递到前台进行解析
					//注意是getResponse而不是getRequest
					ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
					//将最终得到的json形式的字符串传回给前台页面供easyui解析。注意，没有easyui的话是无法解析这些信息的，信息格式不对也是无法解析的，必须是total和rows
					//到前台页面后这些信息会被显示到table里展示符合要求的每一页的信息
					try {
						//回写信息的这一行会抛出异常
						ServletActionContext.getResponse().getWriter().print(jsonString);
					} catch (IOException e) {
						e.printStackTrace();
					}
		}
	
	protected Pageable pageable;
	
	public void setPageable(){
		this.pageable=new PageRequest(page-1, rows);
	}
	
}
