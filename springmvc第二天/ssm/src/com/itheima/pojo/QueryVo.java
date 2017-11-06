package com.itheima.pojo;

import java.util.List;

public class QueryVo {

	private List<Items> itemList;

	public List<Items> getItemList() {
		return itemList;
	}

	public void setItemList(List<Items> itemList) {
		this.itemList = itemList;
	}
	
	//空参构造函数
	public QueryVo() {
		super();
	}

	//带参数的构造函数
	public QueryVo(List<Items> itemList) {
		super();
		this.itemList = itemList;
	}
	
}
