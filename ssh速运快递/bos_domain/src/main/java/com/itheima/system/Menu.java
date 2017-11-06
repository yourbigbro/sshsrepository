package com.itheima.system;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @description:菜单
 */
@Entity
@Table(name = "T_MENU")
public class Menu implements Serializable{
	@Id
	@GeneratedValue
	@Column(name = "C_ID")
	private Integer id;
	@Column(name = "C_NAME")
	private String name; // 菜单名称
	@Column(name = "C_PAGE")
	private String page; // 访问路径
	@Column(name = "C_PRIORITY")
	private Integer priority; // 优先级
	@Column(name = "C_DESCRIPTION")
	private String description; // 描述

	@ManyToMany(mappedBy = "menus")
	private Set<Role> roles = new HashSet<Role>(0);

	//取消懒加载，使得集合中的内容立即加载
	@OneToMany(mappedBy = "parentMenu",fetch=FetchType.EAGER)
	private Set<Menu> childrenMenus = new HashSet<Menu>();

	@ManyToOne
	@JoinColumn(name = "C_PID")
	private Menu parentMenu;
	
	public Integer getpId(){
		//pId其实就是parentMenu的id
		//在easyui里面0代表他没有父节点，也就是说他是顶级节点
		//这里不考虑0是不行的，因为假如没有父节点，那么父节点的id就会空指针，前台easyui也就无法展示数据，因为没有父节点的信息
		return parentMenu!=null? parentMenu.getId():0;
	}
	
	//children属性是easyui的treegrid插件所必须的，可以从实例demo里面的json文件看出来
	public Set<Menu> getChildren(){
		return childrenMenus;
	}
	
	public String getText(){
		return name;
	}
	
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

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Set<Menu> getChildrenMenus() {
		return childrenMenus;
	}

	public void setChildrenMenus(Set<Menu> childrenMenus) {
		this.childrenMenus = childrenMenus;
	}

	public Menu getParentMenu() {
		return parentMenu;
	}

	public void setParentMenu(Menu parentMenu) {
		this.parentMenu = parentMenu;
	}

	@Override
	public String toString() {
		return "Menu [id=" + id + ", name=" + name + ", page=" + page + ", priority=" + priority + ", description="
				+ description + ", roles=" + roles + ", childrenMenus=" + childrenMenus + ", parentMenu=" + parentMenu
				+ "]";
	}
	
	

}
