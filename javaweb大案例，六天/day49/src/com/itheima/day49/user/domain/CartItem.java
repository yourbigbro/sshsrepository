package com.itheima.day49.user.domain;

import com.itheima.day49.user.domain.Product;

public class CartItem {
	//数据库中的商品id和商品数量都可以由product对象得来，就暂时不作为成员变量单独列出来了
	//订单对象(由外键而来)
	private Cart cart;
	
	//产品对象，也就是用对象做了成员变量
	private Product product;
	//数量
	private String count;
	//小计（这一种商品的总金额）（可以在内部得到）
	private double totalPrice;
	//设置每个购物项的唯一id
	private String itemId;
	
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public double getTotalPrice() {
		return this.getProduct().getShop_price()*Integer.parseInt(this.getCount());//内部计算单项商品的总金额
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	//将外键作为cart对象引入进来
	public Cart getCart() {
		return cart;
	}
	public void setCart(Cart cart) {
		this.cart = cart;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public CartItem() {
		super();
	}
	public CartItem(Cart cart, Product product, String count, double totalPrice, String itemId) {
		super();
		this.cart = cart;
		this.product = product;
		this.count = count;
		this.totalPrice = totalPrice;
		this.itemId = itemId;
	}
	
}
