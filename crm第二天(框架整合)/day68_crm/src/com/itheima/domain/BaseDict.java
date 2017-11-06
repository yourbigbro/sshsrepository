package com.itheima.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

//该类里面不用写构造方法，最多写个toString方法用于测试展示数据

//表示这是一个实体类
@Entity
//声明这个实体类对应的数据库表
@Table(name="base_dict")
public class BaseDict implements Serializable {
	
	//这是多对一中的一，只有被外键指向的主键，没有外键


	//`dict_id` VARCHAR(32) NOT NULL COMMENT '数据字典id(主键)',
	//`dict_type_code` VARCHAR(10) NOT NULL COMMENT '数据字典类别代码',
	//`dict_type_name` VARCHAR(64) NOT NULL COMMENT '数据字典类别名称',
	//`dict_item_name` VARCHAR(64) NOT NULL COMMENT '数据字典项目名称',
	//`dict_item_code` VARCHAR(10) DEFAULT NULL COMMENT '数据字典项目(可为空)',
	//`dict_sort` INT(10) DEFAULT NULL COMMENT '排序字段',
	//`dict_enable` CHAR(1) NOT NULL COMMENT '1:使用 0:停用',
	//`dict_memo` VARCHAR(64) DEFAULT NULL COMMENT '备注',
	
	//数据库类型不管是char还是varchar在实体类里面都映射为String
	
	@Id
	@GeneratedValue(generator="dictId")
	@GenericGenerator(name="dictId",strategy="uuid")
	@Column(name="dict_id")
	private String dictId;
	
	@Column(name="dict_type_code")
	private String dictTypeCode;
	
	@Column(name="dict_type_name")
	private String dictTypeName;
	
	@Column(name="dict_item_name")
	private String dictItemName;
	
	@Column(name="dict_item_code")
	private String dictItemCode;
	
	@Column(name="dict_sort")
	private Integer dictSort;
	
	@Column(name="dict_enable")
	private String dictEnable;
	
	@Column(name="dict_memo")
	private String dictMemo;
	
	public String getDictId() {
		return dictId;
	}
	public void setDictId(String dictId) {
		this.dictId = dictId;
	}
	public String getDictTypeCode() {
		return dictTypeCode;
	}
	public void setDictTypeCode(String dictTypeCode) {
		this.dictTypeCode = dictTypeCode;
	}
	public String getDictTypeName() {
		return dictTypeName;
	}
	public void setDictTypeName(String dictTypeName) {
		this.dictTypeName = dictTypeName;
	}
	public String getDictItemName() {
		return dictItemName;
	}
	public void setDictItemName(String dictItemName) {
		this.dictItemName = dictItemName;
	}
	public String getDictItemCode() {
		return dictItemCode;
	}
	public void setDictItemCode(String dictItemCode) {
		this.dictItemCode = dictItemCode;
	}
	public Integer getDictSort() {
		return dictSort;
	}
	public void setDictSort(Integer dictSort) {
		this.dictSort = dictSort;
	}
	public String getDictEnable() {
		return dictEnable;
	}
	public void setDictEnable(String dictEnable) {
		this.dictEnable = dictEnable;
	}
	public String getDictMemo() {
		return dictMemo;
	}
	public void setDictMemo(String dictMemo) {
		this.dictMemo = dictMemo;
	}
	
	@Override
	public String toString() {
		return "BaseDict [dictId=" + dictId + ", dictTypeCode=" + dictTypeCode + ", dictTypeName=" + dictTypeName
				+ ", dictItemName=" + dictItemName + ", dictItemCode=" + dictItemCode + ", dictSort=" + dictSort
				+ ", dictEnable=" + dictEnable + ", dictMemo=" + dictMemo + "]";
	}
	
	
}
