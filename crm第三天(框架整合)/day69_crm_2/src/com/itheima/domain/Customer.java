package com.itheima.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="cst_customer")
public class Customer implements Serializable{
	/*cust_id    客户编号(主键)
	cust_name    客户名称(公司名称)。
	cust_source    客户信息来源--
	cust_industry    客户所属行业。
	cust_level   客户级别--
	cust_address   客户联系地址。
	cust_phone  客户联系电话。*/
	
	//里面有两个BaseDict类型的参数
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cust_id")
	private Integer custId;
	
	@Column(name="cust_name")
	private String custName;
	
	//表示这是多对一中的多
	@ManyToOne(targetEntity=BaseDict.class)
	//第一个参数是本数据库表，第二个参数是关联的主表的主键
	@JoinColumn(name="cust_source",referencedColumnName="dict_id")
	private BaseDict custSource;
	
	@Column(name="cust_industry")
	private String custIndustry;
	
	@ManyToOne(targetEntity=BaseDict.class)
	@JoinColumn(name="cust_level",referencedColumnName="dict_id")
	private BaseDict custLevel;
	
	@Column(name="cust_address")
	private String custAddress;
	
	@Column(name="cust_phone")
	private String custPhone;
	
	@OneToMany(targetEntity=LinkMan.class,mappedBy="customer")
	private Set<LinkMan> linkMans = new HashSet<>(0);
	
	
	
	public Set<LinkMan> getLinkMans() {
		return linkMans;
	}
	public void setLinkMans(Set<LinkMan> linkMans) {
		this.linkMans = linkMans;
	}
	public Integer getCustId() {
		return custId;
	}

	public void setCustId(Integer custId) {
		this.custId = custId;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public BaseDict getCustSource() {
		return custSource;
	}

	public void setCustSource(BaseDict custSource) {
		this.custSource = custSource;
	}

	public String getCustIndustry() {
		return custIndustry;
	}

	public void setCustIndustry(String custIndustry) {
		this.custIndustry = custIndustry;
	}

	public BaseDict getCustLevel() {
		return custLevel;
	}

	public void setCustLevel(BaseDict custLevel) {
		this.custLevel = custLevel;
	}

	public String getCustAddress() {
		return custAddress;
	}

	public void setCustAddress(String custAddress) {
		this.custAddress = custAddress;
	}

	public String getCustPhone() {
		return custPhone;
	}

	public void setCustPhone(String custPhone) {
		this.custPhone = custPhone;
	}
	
	@Override
	public String toString() {
		return "Customer [custId=" + custId + ", custName=" + custName + ", custSource=" + custSource
				+ ", custIndustry=" + custIndustry + ", custLevel=" + custLevel + ", custAddress=" + custAddress
				+ ", custPhone=" + custPhone + ", linkMans=" + linkMans + "]";
	}

	
	
	
}
