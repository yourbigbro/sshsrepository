package com.itheima.web.action;

import com.itheima.domain.User;
import com.itheima.service.IUserService;
import com.itheima.service.impl.UserServiceImpl;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class UserAction extends ActionSupport implements ModelDriven<User>{
	private User user=new User();
	@Override
	public User getModel() {
		//直接返回该对象即可
		return user;
	}
	//注意这里根本不传入参数user，因为这是定义函数。user在里面直接用即可
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
