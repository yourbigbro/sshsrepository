package com.itheima.web.action;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.itheima.domain.Customer;
import com.itheima.service.CustomerService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;
//本项目是一个基于注解的xml文件整合(整合了ssh三大框架)。

//注意，struts.xml可以删除，里面只有一个自动重启服务器的鸡肋配置，老师也没讲怎么整合到applicationContext.xml

//下面的两个注解是注解用的东西
	//@RunWith(SpringJUnit4ClassRunner.class)//作用是開啓spring容器,這樣以來配置的bean元素就全部被实例化了
	//@ContextConfiguration("classpath:applicationContext.xml")

@Controller("customerAction")//里面的值和xml文件里面的class属性值相同(这个属性值怎么取值都没影响，和CustomerAction类名无关，赋值为abcd都行)
@Scope("prototype")//设置多例模式

@ParentPackage("struts-default")//指定当前包的父包
@Namespace("/")//指定名称空间
public class CustomerAction extends ActionSupport implements ModelDriven<Customer> {
		
		//ModelDriven接口的实现结构。应该是只要涉及到从前台页面传递参数就用它封装，因此customer对象可以多方法共用
		private Customer customer=new Customer();
		
		//在Action动作类中获得spring容器对象，该工具类获得的spring容器是唯一的
		//注意，即使用工具类的方法获得spring容器，set方法也是必须的，而方法里不能嵌套方法，所以只能在list()和testSave()里引用setCustomerService方法
		//ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(ServletActionContext.getServletContext());
	
		/*@Autowired//只要将spring容器抽取出去了，该注解就是必须要有的，而不是有注解就可以没有配置文件。有注解就没有配置文件那是针对类名上的注解，而不是变量上的注解
		private CustomerService customerService;//该变量能够使用就是因为开启了spring容器
		*/
	
		@Autowired//用注解之后就不用set方法了
		private CustomerService customerService;
		//用于被下面的两个查询和添加方法引用
		/*public void setCustomerService(CustomerService customerService) {
			this.customerService = customerService;
		}*/

		//查找方法
		@Test
		//这个注解叫@Action的原因是他原本存在于struts.xml的action元素中
		//value属性值对应于url，和CustomerAction类名无关
		@Action(value="list_Customer",results={@Result(name="list",type="dispatcher",location="/jsp/customer/list.jsp")})
		public String list(){

			//1.使用service对象查询所有客户
			//注意，该ac对象的方法必须写在方法里面，而不能像获得ac对象一样写在类里面方法外面
			/*customerService = (CustomerService)ac.getBean("customerService");
			this.setCustomerService(customerService);*/
			
			List<Customer> list = customerService.findAllCustomer();
			/*//2.获取request对象
			HttpServletRequest request = ServletActionContext.getRequest();
			//3.存入request域中
			request.setAttribute("list", list);*/
			
			//相对于将list集合放在request域中，还可以将list放在值栈的栈顶，这样jsp页面也可以自动取用
			//获得值栈
			/*ValueStack valueStack = ActionContext.getContext().getValueStack();
			//压入栈顶
			valueStack.push(list);*/
			
			//存储集合的话不能压入栈顶，因为集合里面的元素的名字无法知晓。只有对象(例如customer)才能压入栈顶然后原样取出来。
			//放入map
			ActionContext.getContext().put("list", list);
			
			if(list!=null){
				return "list";
			}
			return null;
		}
		
		//保存方法
		@Test
		public void testSave(){
			Customer c = new Customer();
			c.setCustName("传智学院test");		
			/*customerService = (CustomerService)ac.getBean("customerService");
			this.setCustomerService(customerService);*/
			
			customerService.saveCustomer(c);
		}
		
		//ModelDriven接口的作用是可以方便地封装数据，而不用在action页面中写出一大堆成员变量和get set方法。
		//ModelDriven接口的实现结构
		@Override
		public Customer getModel() {
			return customer;
		}
	
}
