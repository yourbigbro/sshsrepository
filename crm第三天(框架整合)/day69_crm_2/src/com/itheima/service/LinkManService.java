package com.itheima.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.itheima.domain.LinkMan;

public interface LinkManService {

	void add(LinkMan linkMan);

	List<LinkMan> list(DetachedCriteria criteria);

	void edit(LinkMan linkMan);

	void delete(LinkMan linkMan);

	LinkMan findByCriteria(DetachedCriteria criteria);

	LinkMan fuckFind(LinkMan linkMan);

}
