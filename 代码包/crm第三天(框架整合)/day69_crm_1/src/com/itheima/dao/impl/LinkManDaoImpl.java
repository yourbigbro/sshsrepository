package com.itheima.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.itheima.dao.LinkManDao;
import com.itheima.domain.LinkMan;

@Repository("linkManDao")
public class LinkManDaoImpl implements LinkManDao {
	
	@Autowired
	HibernateTemplate hibernateTemplate;
	
	//添加联系人到数据库
	@Override
	public void add(LinkMan linkMan) {
		try {
			
			hibernateTemplate.save(linkMan);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		
	}
	
	//利用离线对象从数据库查询所有的联系人信息
	@Override
	public List<LinkMan> list(DetachedCriteria criteria) {
		return (List<LinkMan>)hibernateTemplate.findByCriteria(criteria);
	}
	
	//修改数据库中某一条联系人的信息
	@Override
	public void edit(LinkMan linkMan) {
		//修改数据库用update
		System.out.println(linkMan);
		hibernateTemplate.update(linkMan);
		
	}
	
	//删除数据库中的某一条联系人信息
	@Override
	public void delete(LinkMan linkMan) {
		hibernateTemplate.delete(linkMan);
		
	}

	//从数据库中查询指定id的LinkMan对象用户回显到edit.jsp页面
	@Override
	public LinkMan findByCriteria(DetachedCriteria criteria) {
		//测试是否从数据库中拿到了数据行(也就是LinkMan对象)
		System.out.println(((List<LinkMan>)hibernateTemplate.findByCriteria(criteria)).size());
		return ((List<LinkMan>)hibernateTemplate.findByCriteria(criteria)).get(0);
	}
	
	//用LinkMan对象从数据库中查询到指定id的内容
	@Override
	//在这个方法就不再往下写了，可以用c3p0
	public LinkMan fuckFind(LinkMan linkMan) {
		Integer lkmId = linkMan.getLkmId();
		return new LinkMan();
	}
}
