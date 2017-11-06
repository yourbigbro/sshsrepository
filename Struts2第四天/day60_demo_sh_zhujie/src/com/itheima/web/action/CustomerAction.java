package com.itheima.web.action;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import com.itheima.domain.Customer;
import com.itheima.service.ICustomerService;
import com.itheima.service.impl.CustomerServiceImpl;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
//@ParentPackage里面的参数当为myDefault时表示经过拦截器，当为struts-default时表示不经过拦截器
@ParentPackage("myDefault")
@Namespace("/")
//当上面是struts-default(也就是不经过拦截器栈)时下面应该配置成defaultStack，也就是使用默认的拦截器栈
@InterceptorRef("myStack")//注意，将他从方法上提出到类之外之后就不用再用数组了
@Results(value={@Result(name="success",location="/jsp/customer/list.jsp"),@Result(name="edit_success",location="/jsp/customer/edit.jsp"),@Result(name="add_success",type="redirectAction",location="/findAllCustomer_Customer.action"),@Result(name="login",location="/login.jsp")})
public class CustomerAction extends ActionSupport implements ModelDriven<Customer>{
	private Customer customer=new Customer();
	@Override
	//注意这个方法的返回值是customer对象，这个方法的作用是为customer赋值，也就是自动的将jsp传来的数据封装到customer对象。与展示信息(从数据库查询信息)无关
	public Customer getModel() {
		return customer;
	}
	
	/*@Override
	public String execute(){
		System.out.println(customer);
		return null;
	}*/
	//展示用户列表
	@Action(value="findAllCustomer_Customer")
	public String findAllCustomer(){
		ICustomerService allCustomer=new CustomerServiceImpl();
		List<Customer> list=allCustomer.findAll();
		//将查询到的list保存到request域对象
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("list",list);
		//通过配置文件映射到list页面，list页面再取出request域中的信息
		return "success";
	}
	
	//将某客户的信息加载到修改客户信息的页面
	
	@Action(value="findCustomer_Customer")
	public String findCustomer(){
		//获取list.jsp页面传来的用户id信息
		HttpServletRequest request = ServletActionContext.getRequest();
		String custId = request.getParameter("custId");
		ICustomerService allCustomer=new CustomerServiceImpl();
		Customer customer=allCustomer.find(custId);
		//将查询到的list保存到request域对象
		HttpServletRequest request2 = ServletActionContext.getRequest();
		request.setAttribute("model",customer);
		//通过配置文件映射到list页面，list页面再取出request域中的信息
		return "edit_success";
	}
	
	//用edit.jsp页面传来的数据修改数据库
	@Action(value="editCustomer_Customer")
	public String editCustomer(){
		ICustomerService allCustomer=new CustomerServiceImpl();
		//用模型驱动的方式封装数据并修改数据库
		System.out.println(customer.getCustId());
		allCustomer.editCustomer(customer);
		
		//将修改过的数据库展示在list.jsp页面中
		List<Customer> list=allCustomer.findAll();
		//将查询到的list保存到request域对象
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("list",list);
		//通过配置文件映射到list页面，list页面再取出request域中的信息
		return "success";
	}
}
