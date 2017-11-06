package com.itheima;

public class Account  {
	/**
	 * 
	 */
	
	private String aname;
	private Double amoney;
	public String getAname() {
		return aname;
	}
	public void setAname(String aname) {
		this.aname = aname;
	}
	public Double getAmoney() {
		return amoney;
	}
	public void setAmoney(Double amoney) {
		this.amoney = amoney;
	}
	
	public Account(String aname, Double amoney) {
		super();
		this.aname = aname;
		this.amoney = amoney;
	}
	public Account() {
		super();
	}
	@Override
	public String toString() {
		return "Account [aname=" + aname + ", amoney=" + amoney + "]";
	}
	
	
	
}
