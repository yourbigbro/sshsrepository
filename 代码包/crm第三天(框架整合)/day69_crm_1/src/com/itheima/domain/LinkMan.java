package com.itheima.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="cst_linkman")
public class LinkMan {
	//`lkm_id` BIGINT(32) NOT NULL AUTO_INCREMENT COMMENT '联系人编号(主键)',
	//`lkm_name` VARCHAR(16) DEFAULT NULL COMMENT '联系人姓名',
	//`lkm_gender` VARCHAR(10) DEFAULT NULL COMMENT '联系人性别',
	//`lkm_phone` VARCHAR(16) DEFAULT NULL COMMENT '联系人办公电话',
	//`lkm_mobile` VARCHAR(16) DEFAULT NULL COMMENT '联系人手机',
	//`lkm_email` VARCHAR(64) DEFAULT NULL COMMENT '联系人邮箱',
	//`lkm_position` VARCHAR(16) DEFAULT NULL COMMENT '联系人职位',
	//`lkm_memo` VARCHAR(512) DEFAULT NULL COMMENT '联系人备注',
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="lkm_id")
	private Integer lkmId;
	
	@Column(name="lkm_name")
	private String lkmName;
	
	@Column(name="lkm_gender")
	private String lkmGender;
	
	@Column(name="lkm_phone")
	private String lkmPhone;
	
	@Column(name="lkm_mobile")
	private String lkmMobile;
	
	@Column(name="lkm_email")
	private String lkmEmail;
	
	@Column(name="lkm_position")
	private String lkmPosition;
	
	@Column(name="lkm_memo")
	private String lkmMemo;
	
	//由于用户和联系人是一对多的关系，所以在实体类中增加了这样一个成员变量
	@ManyToOne(targetEntity=Customer.class)
	@JoinColumn(name="lkm_cust_id",referencedColumnName="cust_id")
	private Customer customer;

	public Integer getLkmId() {
		return lkmId;
	}

	public void setLkmId(Integer lkmId) {
		this.lkmId = lkmId;
	}

	public String getLkmName() {
		return lkmName;
	}

	public void setLkmName(String lkmName) {
		this.lkmName = lkmName;
	}

	public String getLkmGender() {
		return lkmGender;
	}

	public void setLkmGender(String lkmGender) {
		this.lkmGender = lkmGender;
	}

	public String getLkmPhone() {
		return lkmPhone;
	}

	public void setLkmPhone(String lkmPhone) {
		this.lkmPhone = lkmPhone;
	}

	public String getLkmMobile() {
		return lkmMobile;
	}

	public void setLkmMobile(String lkmMobile) {
		this.lkmMobile = lkmMobile;
	}

	public String getLkmEmail() {
		return lkmEmail;
	}

	public void setLkmEmail(String lkmEmail) {
		this.lkmEmail = lkmEmail;
	}

	public String getLkmPosition() {
		return lkmPosition;
	}

	public void setLkmPosition(String lkmPosition) {
		this.lkmPosition = lkmPosition;
	}

	public String getLkmMemo() {
		return lkmMemo;
	}

	public void setLkmMemo(String lkmMemo) {
		this.lkmMemo = lkmMemo;
	}
	
	
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "LinkMan [lkmId=" + lkmId + ", lkmName=" + lkmName + ", lkmGender=" + lkmGender + ", lkmPhone="
				+ lkmPhone + ", lkmMobile=" + lkmMobile + ", lkmEmail=" + lkmEmail + ", lkmPosition=" + lkmPosition
				+ ", lkmMemo=" + lkmMemo + ", customer=" + customer + "]";
	}

	

	
	
	
}
