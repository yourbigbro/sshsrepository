package com.itheima.web.action;

import org.apache.struts2.ServletActionContext;

import com.itheima.domain.Customer;
import com.itheima.service.CustomerService;
import com.opensymphony.xwork2.ActionSupport;

//注意ActionSupport是一个父类不是一个接口
public class CustomerAction extends ActionSupport{
	
	//从配置文件中取到CustomerService
	private CustomerService customerService;
	
	public CustomerService getCustomerService() {
		return customerService;
	}

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

		//封装前台页面传来的页面
		private Long custId;

		public Long getCustId() {
			return custId;
		}

		public void setCustId(Long custId) {
			this.custId = custId;
		}
		
		//该函数不需要有参数也不能有参数，因为custId是作为全局变量的而不是作为参数的
		public String findCustById(){
			Customer customer=customerService.findCustById(custId);
			ServletActionContext.getRequest().setAttribute("customer",customer);
			//能使用常量SUCCESS是ActionSupport类的结果
			return SUCCESS;
		}
}
