package com.itheima.system;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * @description:权限名称
 */
@Entity
@Table(name = "T_PERMISSION")
public class Permission implements Serializable{

	@Id
	@GeneratedValue
	@Column(name = "C_ID")
	private Integer id;
	@Column(name = "C_NAME")
	private String name; // 权限名称
	@Column(name = "C_KEYWORD")
	private String keyword; // 权限关键字，用于权限控制
	@Column(name = "C_DESCRIPTION")
	private String description; // 描述

	@ManyToMany(mappedBy = "permissions")
	//用0初始化hashset长度是为了避免用默认长度初始化集合造成内存的浪费
	
	/*下面的多对多用set集合而不用list集合的原因：
	其实hibernate是有提供list映射的， list就是bag类型，bag适合关联的集合类中有排序需求
	在配置list的时候，也有几种方法：指定list-index，这也就要在数据库中对应创建一个字段表示顺序
	对于一对多关联当中的List，需要在数据库里面维护一个index列，如果List当中的某个元素被删除，那么Hibernate会
	连续发送多条update语句，更新后续所有元素的index列，以确保index的连续性(在inverse为false的情况下)，如果
	你选择自己维护index列，也同样会面临这个问题，甚至更棘手(在inverse为true的情况下)，所以List被谨慎的使用在
	极其罕见的场合。*/
	private Set<Role> roles = new HashSet<Role>(0);

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
