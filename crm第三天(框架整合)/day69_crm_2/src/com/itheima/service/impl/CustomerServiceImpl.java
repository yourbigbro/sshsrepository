package com.itheima.service.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.dao.CustomerDao;
import com.itheima.domain.BaseDict;
import com.itheima.domain.Customer;
import com.itheima.service.CustomerService;
import com.itheima.web.action.PageBean;

@Service("customerService")
@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	private CustomerDao customerDao;

	@Override
	@Transactional(readOnly=true,propagation=Propagation.SUPPORTS)
	public List<BaseDict> findCustSource() {
		return customerDao.findCustSource("002");
	}

	@Override
	@Transactional(readOnly=true,propagation=Propagation.SUPPORTS)
	public List<BaseDict> findCustLevel() {
		return customerDao.findCustSource("006");
	}
	
	//保存新用户
	@Override
	public void saveCustomer(Customer customer) {
		customerDao.saveCustomer(customer);
	}
	
	//查询出所有的Customer(用户)
	@Override
	@Transactional(readOnly=true,propagation=Propagation.SUPPORTS)
	public List<Customer> listAllCustomer() {
		return customerDao.listAllCustomer();
	}
	
	//根据id查询Customer对象
	@Override
	public Customer queryByCustId(Integer custId) {
		return customerDao.queryByCustId(custId);
	}
	
	//修改用户信息
	@Override
	public void reSaveCustomer(Customer customer) {
		customerDao.reSaveCustomer(customer);
		
	}
	
	//删除一条用户信息
	@Override
	public void removeCustomer(Customer customer) {
		customerDao.removeCustomer(customer);
		
	}
	
	//筛选信息
	@Override
	public List<Customer> findCustomersByCriteria(DetachedCriteria criteria) {
		return customerDao.findCustomersByCriteria(criteria); 
	}
	
	//根据criteria对象查询当页的对象集合，这个方法不是从Action传来的，而是从本层开始的
	public List<Customer> queryCustomerList(DetachedCriteria criteria,int startIndex,int pageSize){
		return customerDao.queryCustomerList(criteria,startIndex,pageSize);
	}
	
	//根据改造后的criteria对象求出数据库中的记录总数，这个方法不是从Action传来的，而是从本层开始的
	public int queryCount(DetachedCriteria criteria){
		List<Long> count2=customerDao.queryCount(criteria);
		if(count2==null){
			return 0;
		}else {
			return count2.get(0).intValue();
		}
	}

	//分页查询记录
	@Override
	//注意要先传入总记录数再计算分页的页数，不然总记录数count未定义的话默认是0，总页数要依据count计算，自然也是0
	public PageBean<Customer> listCurrentPageCustomer(DetachedCriteria criteria, int currentPage, int pageSize) {
		
		//对criteria对象进行改变使他查询记录的条数，得出count并传入PageBean
		criteria.setProjection(Projections.rowCount());
		int count=this.queryCount(criteria);
		
		PageBean<Customer> pageBean = new PageBean<Customer>(count,currentPage,pageSize);
		
		//将criteria对象恢复原状，也就是从count(*)恢复到*
		criteria.setProjection(null);
		int startIndex=pageBean.getStartIndex();
		
		
		//调用上方刚刚定义的queryCustomerList方法
		System.out.println(startIndex+"-------------------"+pageBean.getCurrentPage());
		List<Customer> list=this.queryCustomerList(criteria, startIndex, pageSize);
		System.out.println(list.get(0).toString());
		//最终完成pageBean的封装
		pageBean.setList(list);
		
		
		
		//将封装好的pageBean返回前台页面
		return pageBean;
						
	}

}
