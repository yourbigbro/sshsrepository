package com.itheima.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.dao.LinkManDao;
import com.itheima.domain.LinkMan;
import com.itheima.service.LinkManService;

@Service("linkManService")
@Transactional
public class LinkManServiceImpl implements LinkManService {
	
	@Autowired
	private LinkManDao linkManDao;
	
	//添加联系人到数据库
	@Override
	public void add(LinkMan linkMan) {
		linkManDao.add(linkMan);
		
	}
	
	//利用离线对象查询数据库中的所有信息
	@Override
	public List<LinkMan> list(DetachedCriteria criteria) {
		return linkManDao.list(criteria);
	}
	
	//修改数据库中某个联系人信息
	@Override
	public void edit(LinkMan linkMan) {
		linkManDao.edit(linkMan);
		
	}
	
	//删除数据库中的某一条联系人信息
	@Override
	public void delete(LinkMan linkMan) {
		linkManDao.delete(linkMan);
		
	}

	//查询指定id的LinkMan对象(用criteria对象)
	@Override
	public LinkMan findByCriteria(DetachedCriteria criteria) {
		return linkManDao.findByCriteria(criteria);
	}
	
	//查询指定id的LinkMan对象(用LinkMan对象)
	@Override
	public LinkMan fuckFind(LinkMan linkMan) {
		return linkManDao.fuckFind(linkMan);
	}
}
