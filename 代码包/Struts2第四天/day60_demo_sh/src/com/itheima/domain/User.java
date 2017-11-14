package com.itheima.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name="cst_user")
public class User implements Serializable {
	//假如主键不是long类型而是String类型的话应该如下配置：
	@Id
	@GeneratedValue(generator="xxx")
	@GenericGenerator(name="xxx",strategy="uuid")
	private String uid;
	@Column(name="username")
	private String username;
	@Column(name="password")
	private String password;
	//只要get和set就够了，不用两个构造函数
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
