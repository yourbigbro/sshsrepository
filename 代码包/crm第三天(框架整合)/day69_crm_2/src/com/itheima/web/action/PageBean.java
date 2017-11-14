package com.itheima.web.action;

import java.util.List;

//各个get/set方法的顺序和是否能取到值没有关系
public class PageBean<T> {
	
	//总页数(可以内部计算得出)
	private int totalPage;
	//总记录数
	private int count;
	//当页起始索引(可以内部计算得出)
	private int startIndex;
	//当页的对象集合
	private List<T> list;
	//当前页码
	private int currentPage;
	//每页的显示条数(也就是页的大小)
	private int pageSize;
	
	//前一页的页码(可以内部求出)
	private int prePage;
	//后一页的页码(可以内部求出)
	private int nextPage;
	
	//下面是所有的get/set方法
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	public PageBean(){
		
	}
	
	//该构造函数传入了前台页面传来的两个参数外加查询得出的数据总数count
	public PageBean(int count, int currentPage, int pageSize) {
		
		//这里面只有list没有进行初始化
		//注意，初始化是有顺序的，不能随意调换顺序，只有前面的求出来了才可以求后面的
		
		this.count = count;
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		
		this.totalPage=count%pageSize==0?(count/pageSize):(count/pageSize+1);
		
		this.startIndex=(currentPage-1)*pageSize;
		
		this.prePage=(currentPage>1)?(currentPage-1):1;
		
		this.nextPage=(currentPage<totalPage)?(currentPage+1):totalPage;
	}
	
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	
	public int getStartIndex() {
		///内部计算当页的起始索引
		return startIndex;
	}
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	//计算上一页
	public int getPrePage() {
		return prePage;
	}
	public void setPrePage(int prePage) {
		this.prePage = prePage;
	}
	//计算下一页
	public int getNextPage() {
		return nextPage;
	}
	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}
	
	
}
