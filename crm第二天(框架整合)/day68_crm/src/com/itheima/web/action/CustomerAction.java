package com.itheima.web.action;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.itheima.domain.BaseDict;
import com.itheima.domain.Customer;
import com.itheima.service.CustomerService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;


//该项目主要用于演示字典表和用户信息的增删改查和筛选。字典表和customer表是一对多的关系，因为字典表中的一项可对应多个用户
@Controller("customerAction")
@Scope("prototype")
@ParentPackage("struts-default")
@Namespace("/")
public class CustomerAction extends ActionSupport implements ModelDriven<Customer> {
	
	//不仅是对象的属性能封装进来，就连对象的属性(也是一个对象，BaseDict)的属性也能封装进来
	private Customer customer=new Customer();
	
	//模型驱动不用getset方法，需要get方法的是加入值栈方便前台页面获取的那个。
	@Override
	public Customer getModel() {
		
		return customer;
	}
	
	
	
	@Autowired
	private CustomerService customerService;
	
	//注意，不要命名为custSource，因为会和Customer实体类中的属性重名，导致页面获取值栈中的内容时出错！！！！！！！！！！！！
	//客户来源表(行)
	private List<BaseDict> custSources;
	//客户等级表(行)
	private List<BaseDict> custLevels;
	
	
	//写出两个get方法，前台jsp页面才能从值栈中获得这两个私有变量
	public List<BaseDict> getCustSources() {
		return custSources;
	}


	public List<BaseDict> getCustLevels() {
		return custLevels;
	}
	
	//该方法用于添加新用户
	@Action(value="addCustomerUI",results={@Result(name="add",location="/jsp/customer/add.jsp")})
	public String add(){
		//为两个集合变量赋值
		custSources=customerService.findCustSource();
		custLevels=customerService.findCustLevel();  
		/*System.out.println(custSource);
		System.out.println(custLevel);*/
		//下面的方法是放在session中，当然还可以放在request中
		/*ActionContext.getContext().getSession().put("custLevel", custLevel);
		ActionContext.getContext().getSession().put("custSource", custSource);*/
		return "add";
	}

	//该方法用于将新添加的用户信息保存到数据库(也就是保存用户)。注意，location的属性链接和前台页面的链接形式相同，都是listCustomer.action
	@Action(value="addCustomer",results={@Result(name="listCustomer",type="redirectAction",location="listCustomer.action")})
	public String addCustomer(){
		
		//注意，add方法和save方法要分开，因为对于mysql数据库主键自增时，假如存入相同主键，自增策略会无视用户手动存入的主键，
		//用Hibernate封装的save方法则可以修改原来已存在的记录。存入一条新数据要用save方法。自动覆盖原有记录那是redis数据库
		
		//将用户对象保存到数据库
		customerService.saveCustomer(customer);
		//跳转到查询数据库中所有员工信息的方法
		return "listCustomer";
	}
	
	//创建customers对象并将它压入值栈。注意这是全局变量
	private List<Customer> customers;
	
	
	public List<Customer> getCustomers() {
		return customers;
	}


	//查询数据库并返回对象
	@Action(value="listCustomer",results={@Result(name="list",location="/jsp/customer/list.jsp")})
	public String list(){
		
		//这两个查询的作用是在直接点击客户列表时可以为上面的筛选用的下拉列表赋值。
		custSources=customerService.findCustSource();
		custLevels=customerService.findCustLevel();
		
		//注意不要将customers定义成局部变量，他是全局变量。假如定义成局部变量，那么全局变量就为空，当然页面也就拿不到信息。
		customers=customerService.listAllCustomer();
		
		return "list";
	}
	
	private Customer customer2;
	
	public Customer getCustomer2() {
		return customer2;
	}


	//修改list页面的某一行信息
	@Action(value="editCustomerUI",results={@Result(name="edit",location="/jsp/customer/edit.jsp")})
	public String modify(){
		//根据封装好的customer对象重新获得custId并且从数据库查到完整的对象赋值给customer2，继而传递到add页面。当主键相同时数据库应该会自动覆盖原来的记录。
		Integer custId = customer.getCustId();
		//从数据库中查到该id所对应的customer对象并给customer2赋值
		customer2=customerService.queryByCustId(custId);
		//跳转到edit.jsp页面进行数据回显，以进行编辑用户信息
		
		/*//要知道，选项和查得的信息根本无关，一直是这个代码
		custSources=customerService.findCustSource();
		custLevels=customerService.findCustLevel();*/
		
		return "edit";
	}
	//回写修改后的信息，然后再次在list页面展示出来
	@Action(value="changeCustomer",results={@Result(name="listCustomer",type="redirectAction",location="listCustomer.action")})
	public String changeCustomer(){
		//传入模型驱动/属性驱动封装好的对象
		customerService.reSaveCustomer(customer);
		return "listCustomer";
	}
	
	//删除list页面的某一行信息，然后再将所有用户信息回显到list.jsp页面
	@Action(value="removeCustomer",results={@Result(name="listCustomer",type="redirectAction",location="listCustomer.action")})
	public String removeCustomer(){
		customerService.removeCustomer(customer);
		return "listCustomer";
	}
	
	//筛选list页面的信息(依然是为全局变量customers赋值)
	//注意直接到list.jsp页面，不用再经过展示页面的方法，因为已经获取到customers对象了
	@Action(value="chooseCustomer",results={@Result(name="list",location="/jsp/customer/list.jsp")})
	public String chooseCustomer(){
		//提示中的clazz指的是Customer.class的形式，也就是字节码文件
		DetachedCriteria criteria=DetachedCriteria.forClass(Customer.class);
		
		//分别检验四个参数是否传入，如果传入就将参数传入criteria对象
		
		if(StringUtils.isNoneBlank(customer.getCustName())){
			criteria.add(Restrictions.like("custName", "%"+customer.getCustName()+"%"));
		}
		
		if(StringUtils.isNotBlank(customer.getCustIndustry())){
			criteria.add(Restrictions.like("custIndustry", "%"+customer.getCustIndustry()+"%"));
		}
		
		if(customer.getCustSource()!=null&&StringUtils.isNotBlank(customer.getCustSource().getDictId())){
			//注意第一个字符串参数的形式，是customer对象的属性值，我猜用的是反射。第二个参数自然由customer对象获得
			criteria.add(Restrictions.eq("custSource.dictId", customer.getCustSource().getDictId()));
		}
		
		if(customer.getCustLevel()!=null&&StringUtils.isNotBlank(customer.getCustLevel().getDictId())){
			criteria.add(Restrictions.eq("custLevel.dictId", customer.getCustLevel().getDictId()));
		}
		
		//封装好了criteria对象之后将他应用于数据库查询，dao层的HibernateTemplate对象有用它查询数据库的方法(也就是以他为参数)
		//依然将他封装到全局变量customers
		//System.out.println(criteria.toString());
		customers=customerService.findCustomersByCriteria(criteria);
		
		//这两句如果不写的话就会使得下拉菜单没选项。为什么？？？？？？？？？？？？？？？？？？？？？？？？？？？？？
		custSources = customerService.findCustSource();
		custLevels = customerService.findCustLevel();
		return "list";
	}
}
