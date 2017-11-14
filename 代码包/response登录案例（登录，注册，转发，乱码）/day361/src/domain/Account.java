package domain;


public class Account {
	private String ausername;
	private String apassword;
	private String hobby;
	public String getAusername() {
		return ausername;
	}
	public void setAusername(String ausername) {
		this.ausername = ausername;
	}
	public String getApassword() {
		return apassword;
	}
	public void setApassword(String apassword) {
		this.apassword = apassword;
	}
	public String getHobby() {
		return hobby;
	}
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Account(String ausername, String apassword, String hobby) {
		super();
		this.ausername = ausername;
		this.apassword = apassword;
		this.hobby = hobby;
	}
	@Override
	public String toString() {
		return "Account [ausername=" + ausername + ", apassword=" + apassword + ", hobby=" + hobby + "]";
	}
	
}
