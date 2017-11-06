package com.itheima.web.action;

import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.ActionSupport;


//以实现接口的方式访问Servlet的api。继承ActionSupport
public class Demo4Action extends ActionSupport implements ServletRequestAware,ServletContextAware{
	//利用接口提供的方法给两个域对象赋值。不用记类型，下面的两个方法的参数类型就是这两个成员变量的类型
	private HttpServletRequest request; 
	private ServletContext application;
	
	public String execute(){
		Map<String, String[]> para = request.getParameterMap();
		Set<String> keySet = para.keySet();
		for (String string : keySet) {
			
		}
		//假如不extends ActionSupport的话是不会有大写的SUCCESS常量的
		return SUCCESS;
	}
	@Override
	public void setServletContext(ServletContext application) {
		this.application=application;
		
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;
		
	}

}
