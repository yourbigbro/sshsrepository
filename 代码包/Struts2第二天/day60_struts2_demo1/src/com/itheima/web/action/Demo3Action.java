package com.itheima.web.action;

import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;



//以原生方式(耦合方式)访问Servlet的api。继承ActionSupport
public class Demo3Action extends ActionSupport{
	public String execute(){
		//获取request域对象
		HttpServletRequest request = ServletActionContext.getRequest();
		//获取application域对象。注意不是getContext而是getServletContext。比较特殊
		ServletContext application = ServletActionContext.getServletContext();
		//获取session域对象
		HttpSession session = request.getSession();
		//从request域对象里面获取jsp页面传来的信息
		Map<String, String[]> para = request.getParameterMap();
		Set<Entry<String,String[]>> entrySet = para.entrySet();
		for (Entry<String, String[]> entry : entrySet) {
			System.out.println(entry.getKey()+"  "+Arrays.toString(entry.getValue()));
		}
		return SUCCESS;
	}
}
