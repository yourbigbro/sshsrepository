package com.itheima.domain;

import java.util.List;

public class pageBean<T> {//泛型。定义的时候用泛型，实例化的时候不再用泛型，而用Product类名填充
	//总记录数
	private int total;//名字固定，否则页面无法解析json
	//页面大小
	private int pageSize;
	//当前显示的页码
	private int pageNumber;
	//当前页的对象集合
	private List<T> rows;//名字固定，否则页面无法解析json
	//总页数
	private int totalPage;
	//当前页的起始索引
	private int startIndex;
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public List<T> getRows() {//泛型
		return rows;
	}
	public void setRows(List<T> rows) {//泛型
		this.rows = rows;
	}
	//不用传进来的参数，在pagebean内部进行设置即可（即只用其他成员变量就可以得到）
	public int getTotalPage() {
		return this.getTotal()%this.getPageSize()==0?(this.getTotal()/this.getPageSize()):(this.getTotal()/this.getPageSize()+1);
	}
	//下面的set方法不用写，因为没用
	/*public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}*/
	//不用传进来的参数，在pagebean内部进行设置即可（即只用其他成员变量就可以得到）
	public int getStartIndex() {//不设置就行，因为根本不会用到这个值。他的计算实际上是写到了dao里面。
		return startIndex;
	}
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	public pageBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public pageBean(int total, int pageSize, int pageNumber, List<T> rows, int totalPage, int startIndex) {
		super();
		this.total = total;
		this.pageSize = pageSize;
		this.pageNumber = pageNumber;
		this.rows = rows;
		this.totalPage = totalPage;
		this.startIndex = startIndex;
	}
	
}
