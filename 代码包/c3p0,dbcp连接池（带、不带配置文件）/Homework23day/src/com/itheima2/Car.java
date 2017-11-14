package com.itheima2;

public class Car {
	private int id; //ID
	private String cname; //��������
	private String company; //����
	private String address; //����
	private String grade; //����
	private double price; //�۸�
	
	public Car() {
		super();
	}
	public Car(String cname, String company, String address, String grade, double price) {
		super();
		this.cname = cname;
		this.company = company;
		this.address = address;
		this.grade = grade;
		this.price = price;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Car [id=" + id + ", cname=" + cname + ", company=" + company + ", address=" + address + ", grade="
				+ grade + ", price=" + price + "]";
	}
}
