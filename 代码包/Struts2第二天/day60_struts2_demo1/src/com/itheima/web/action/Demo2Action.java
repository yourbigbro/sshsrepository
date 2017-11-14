package com.itheima.web.action;

import java.util.Map;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

//做一些三种域相关的练习。继承ActionSupport
public class Demo2Action extends ActionSupport{
	public String execute(){
		//获得ActionContext实例化对象(相当于request)
		ActionContext context = ActionContext.getContext();
		//要想得到request域的话只能用HttpServletRequest request = ServletActionContext.getRequest();而不能用ActionContext
		//request.setAttribute("name1", "request");
		//注意不用setAttribute而要用put
		context.put("name1","context");
		//在context的基础上获得session域对象
		Map<String, Object> session = context.getSession();
		session.put("name1","session" );
		//在context的基础上获得application域对象
		Map<String, Object> application = context.getApplication();
		application.put("name1", "application");
		return SUCCESS;
	}
}
