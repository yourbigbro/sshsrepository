package com.itheima.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.itheima.domain.LinkMan;

public interface LinkManDao {

	void add(LinkMan linkMan);

	List<LinkMan> list(DetachedCriteria criteria);

	void edit(LinkMan linkMan);

	void delete(LinkMan linkMan);

	LinkMan findByCriteria(DetachedCriteria criteria);

	LinkMan fuckFind(LinkMan linkMan);

}
