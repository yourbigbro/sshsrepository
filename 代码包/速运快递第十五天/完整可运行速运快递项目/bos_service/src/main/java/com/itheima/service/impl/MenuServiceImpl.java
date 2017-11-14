package com.itheima.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.dao.MenuDao;
import com.itheima.service.MenuService;
import com.itheima.system.Menu;
import com.itheima.system.User;

@Service
@Transactional
public class MenuServiceImpl implements MenuService {

	@Autowired
	private MenuDao menuDao;

	@Override
	public List<Menu> findAll() {
		return menuDao.findAll();
	}

	//该方法用于查询顶级数据(也就是没有父菜单的数据)
	@Override
	public List<Menu> findByParentMenuIsNull() {
		return menuDao.findByParentMenuIsNull();
	}

	//该方法用于保存新的Menu对象
	@Override
	public void save(Menu model) {

		menuDao.save(model);
	}

	//根据用户对象动态显示菜单
	@Override
	public List<Menu> findByUser(User user) {
		
		return menuDao.findByUser(user.getId());
	}

}
