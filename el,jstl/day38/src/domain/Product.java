package domain;

import java.io.Serializable;

public class Product implements Serializable{

	private static final long serialVersionUID = 1L;
	private String pid;
	private String pname;
	private String pprice;
	private String pdesc;
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getPprice() {
		return pprice;
	}
	public void setPprice(String pprice) {
		this.pprice = pprice;
	}
	public String getPdesc() {
		return pdesc;
	}
	public void setPdesc(String pdesc) {
		this.pdesc = pdesc;
	}
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Product(String pid, String pname, String pprice, String pdesc) {
		super();
		this.pid = pid;
		this.pname = pname;
		this.pprice = pprice;
		this.pdesc = pdesc;
	}
	
}
