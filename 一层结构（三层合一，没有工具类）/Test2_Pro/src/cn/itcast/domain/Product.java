package cn.itcast.domain;

public class Product {
	private int id;
	private String pname;
	private Double price;
	private int stock;
	private int discount;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Product(int id, String pname, Double price, int stock, int discount) {
		super();
		this.id = id;
		this.pname = pname;
		this.price = price;
		this.stock = stock;
		this.discount = discount;
	}
	
}
