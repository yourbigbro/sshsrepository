package domain;

import java.io.Serializable;

public class User implements Serializable{
	private String id;
	private String username;
	private String birthday;
	private String email;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(String id, String username, String birthday, String email) {
		super();
		this.id = id;
		this.username = username;
		this.birthday = birthday;
		this.email = email;
	}
	
}
