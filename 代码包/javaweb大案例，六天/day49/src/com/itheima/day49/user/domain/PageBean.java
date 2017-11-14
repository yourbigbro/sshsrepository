package com.itheima.day49.user.domain;

import java.util.List;

public class PageBean<T> {
	//当前页码(传进来)，默认为1
	private int pageNumber;
	//当前页的商品对象集合(dao层查得)
	private List<T> rows;
	//每页的商品数量(传进来)，为12
	private int pageSize;
	//商品总数(数据库查询得出)
	private int total;
	//当前页的开始索引(计算得来，不需要pageBean以外的信息)
	private int startIndex;
	//总页数(计算得来，不需要pageBean以外的信息)
	private int totalPage;
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getStartIndex() {
		//计算
		return (this.getPageNumber()-1)*this.getPageSize();
	}
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	public int getTotalPage() {
		//计算
		return this.getTotal()%this.getPageSize()==0?this.getTotal()/this.getPageSize():(this.getTotal()/this.getPageSize()+1);
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public PageBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PageBean(int pageNumber, List<T> rows, int pageSize, int total, int startIndex, int totalPage) {
		super();
		this.pageNumber = pageNumber;
		this.rows = rows;
		this.pageSize = pageSize;
		this.total = total;
		this.startIndex = startIndex;
		this.totalPage = totalPage;
	}
	
	
}
