package com.itheima.web.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import com.itheima.domain.User;
import com.itheima.service.IUserService;
import com.itheima.service.impl.UserServiceImpl;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
//这两项写在类的外面

@ParentPackage("myDefault")
@Namespace("/")//注意是Namespace不是Namespaces
public class UserAction extends ActionSupport implements ModelDriven<User>{
	private User user=new User();
	@Override
	public User getModel() {
		//直接返回该对象即可
		return user;
	}
	//注意这里根本不传入参数user，因为这是定义函数。user在里面直接用即可
	//该类里面目前只有这一个方法
	//每个方法上面都有一个Action,Action里面有最多三个属性
	@Action(value="checkUser_User",results={@Result(name="login",location="/login.jsp"),@Result(name="success",location="/index.jsp")},interceptorRefs={@InterceptorRef("myStack")})
	public String checkUser(){
		IUserService service=new UserServiceImpl();
		User user2=service.checkUser(user);
		if(user2==null){
			System.out.println("登录失败");
			return "login";
		}else {
			//将user对象放入session中
			ActionContext context = ActionContext.getContext();
			context.getSession().put("user", user2);
			return "success";
		}
	}
}
