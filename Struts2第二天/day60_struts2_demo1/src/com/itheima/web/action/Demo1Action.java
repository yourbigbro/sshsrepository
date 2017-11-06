package com.itheima.web.action;

import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

//以完全解耦的方式访问Servlet的api。继承ActionSupport
public class Demo1Action extends ActionSupport{
	public String execute(){
		//类似于获得了request对象
		ActionContext context = ActionContext.getContext();
		//获得集合
		Map<String, Object> para = context.getParameters();
		//从集合中获得键值对
		Set<Entry<String, Object>> entrySet = para.entrySet();
		//遍历集合，注意键值对中的值是对象，要先强制转换成数组，而ServletActionContext是数组，不需要强制转换
		for (Entry<String, Object> entry : entrySet) {
			System.out.println(entry.getKey()+"  "+Arrays.toString((String[])entry.getValue()));
		}
		return SUCCESS;
	}
}
