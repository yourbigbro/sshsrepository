package com.itheima.web.action;

import java.util.Map;
import com.itheima.domain.User;
import com.opensymphony.xwork2.ActionSupport;

public class Demo5Action extends ActionSupport{
	private Map<String, User> map;
	
	public Map<String, User> getMap(){
		return map;
	}
	
	public void setMap(Map<String, User> map){
		this.map=map;
	}
	
	public String execute(){
		for (Map.Entry<String, User> entry: map.entrySet()) {
			System.out.println(entry.getKey()+"  "+entry.getValue());
		}
		
		return null;
	}
}
