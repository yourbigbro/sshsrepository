package com.itheima.day49.user.domain;

import java.util.Date;
import java.util.List;

public class Cart {
	//用户信息
	private User user;
	//订单时间
	private Date date;
	//订单编号
	private String num;
	//订单支付状态(0代表未支付，1代表已经支付)
	private int state;
	//购物车内的商品集合（逐个将商品添加进去）
	private List<CartItem> cartAll;
	//所有商品的总价格（可以在内部计算得出）
	private double allPrice;
	
	public List<CartItem> getCartAll() {
		return cartAll;
	}
	public void setCartAll(List<CartItem> cartAll) {
		this.cartAll = cartAll;
	}
	public double getAllPrice() {
		double allThePrice = 0;
		for (CartItem cartItem : cartAll) {
			allThePrice+=cartItem.getTotalPrice();//将每一种商品的总价格相加
		}
		return allThePrice;
	}
	public void setAllPrice(double allPrice) {
		this.allPrice = allPrice;
	}
	//用户信息（对象）
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	//订单生成时间
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	//订单编号
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	//订单支付状态
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	
	public Cart() {
		super();
		
	}
	public Cart(User user, Date date, String num, int state, List<CartItem> cartAll, double allPrice) {
		super();
		this.user = user;
		this.date = date;
		this.num = num;
		this.state = state;
		this.cartAll = cartAll;
		this.allPrice = allPrice;
	}
	
	
}
