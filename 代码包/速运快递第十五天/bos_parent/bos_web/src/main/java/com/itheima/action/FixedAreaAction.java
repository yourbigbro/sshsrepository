package com.itheima.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import com.itheima.crm.Customer;
import com.itheima.customerService.CustomerService;
import com.itheima.domain.FixedArea;
import com.itheima.service.FixedAreaService;
import com.itheima.service.SubAreaService;
import com.itheima.utils.action.common.CommonAction;


//该类用于定区
public class FixedAreaAction extends CommonAction<FixedArea>{
	
	@Autowired
	private FixedAreaService fixedAreaService;
	
	@Autowired
	private CustomerService customerService;
	
	//注意，保存完之后都是要回到原来的页面重新进行数据展示
	@Action(value="fixedAreaAction_save",results={@Result(name="success",type="redirect",location="/pages/base/fixed_area.html")})
	public String saveFixedArea(){
		
		//将模型驱动的model对象用于保存
		fixedAreaService.save(model);
		return SUCCESS;
	}
	
	//从数据库中查询数据并将对象转换成字符串并在前台页面的table元素中展示数据
	@Action("fixed_area")
	//注意，提取公共类之后的分页方法只有这个三句话
	public void list(){
		//给pageable赋值
		this.setPageable();
		//从数据库中查询得到信息
		Page<FixedArea> pageData=fixedAreaService.find(pageable);
		//将查询的到的pageData对象转化为字符串并传回前台(前端页面)
		this.java2json(pageData,new String[]{"subareas","couriers"});
	}
	
	
	
	//测试cxf是否能够成功运行
	@Action("hehe")
	public void hehe(){
		List<Customer> findAll = customerService.findAll();
		System.out.println(findAll.toString());
	}
	
	//该方法用于查询所有的没有定区的项目(用于填充左边表单)
	@Action("findCustomerWithoutFixedArea")
	public String findCustomerWithoutFixedArea(){
		
		//引用cxf服务中的方法
		List<Customer> list=customerService.findByFixedAreaIdIsNull();
		//这一步同时完成了数据的转换和向前台页面的返回
		this.java2json(list,null);
		//注意，向前台页面返回字符串数据的返回值都是NONE
		return NONE;
	}
	
	//在数据库中查询所有编号为id的项目(用于填充右边表单)
	@Action("findCustomerWithFixedAreaIdIsId")
	public String findCustomerWithFixedAreaIdIsId(){
		//从数据库中查询所有定区编码为fixedAreaId的customer对象的集合
		//注意，从这里点进去不能查看findByFixedAreaId方法的实现方法，因为这是引用的别人的服务，只能查看接口
		List<Customer> list=customerService.findByFixedAreaId(model.getId());
		this.java2json(list,null);
		//将右边表单中的fixedAreaId都变成null(当然没有返回值)
		customerService.setFixedAreaIdNull(list);
		return NONE;
	}
	
	//属性驱动获得前台传来的字符串
	private String str;
	
	public void setStr(String str) {
		this.str = str;
	}

	//将右边复选框中的所有项目改变属性值
	@Action("changeFixedAreaId")
	public String changeFixedAreaId(){
		//解析前台传来的str字符串，首先将它变回到数组
		String[] split = str.split(",");
		//首先取出第一个数组元素作为fixedAreaId属性
		String theFixedAreaId=split[0];
		//遍历剩余的元素得到他们的(也就是这些customer对象的)id并操作数据库改变他们的fixedAreaId属性
		for(int i=1;i<split.length;i++) {
			//将String类型转化为Integer类型
			int st = Integer.parseInt(split[i]);
			//操作数据库(传入两个参数)
			customerService.changeFixedAreaIdById(theFixedAreaId,st);
		}
		return NONE;
	}
	
	//属性驱动接受两个前台表单传来的属性，分别是两个实体类的id
	private Integer courierId;
	
	private Integer takeTimeId;
	
	public void setCourierId(Integer courierId) {
		this.courierId = courierId;
	}

	public void setTakeTimeId(Integer takeTimeId) {
		this.takeTimeId = takeTimeId;
	}
	
	
	
	//该方法用于关联快递员(关联派送时间和快递员)
	@Action("fixedArea_associationCourierToFixedArea")
	public void fixedArea_associationCourierToFixedArea(){
		
		fixedAreaService.fixedArea_associationCourierToFixedArea(courierId,takeTimeId,model.getId());
	}
	
	//模型驱动接收前台传来的属性值(一长串随机生成的码)
	private String[] customerIds;

	public void setCustomerIds(String[] customerIds) {
		this.customerIds = customerIds;
	}
	
	@Autowired
	private SubAreaService subAreaService;
	
	//该方法的作用是在表单提交时将某个或某些分区关联给某个定区
	@Action("subarea")
	public String subarea(){
		
		//从模型驱动中获得隐藏域中的定区id
		String id = model.getId();
		
		fixedAreaService.subarea(id,customerIds);
		
		return NONE;
	}
}
