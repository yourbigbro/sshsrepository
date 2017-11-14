package com.itheima.demo4;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class CollectionBean {
	private String[] arr;
	private List list;
	private Set set;
	private Map map;
	private Properties prop;
	
	//只有set方法
	public void setArr(String[] arr) {
		this.arr = arr;
	}
	public void setList(List list) {
		this.list = list;
	}
	public void setSet(Set set) {
		this.set = set;
	}
	public void setMap(Map map) {
		this.map = map;
	}
	public void setProp(Properties prop) {
		this.prop = prop;
	}
	
}
