package com.itheima.service;

import java.util.List;

import com.itheima.system.Menu;
import com.itheima.system.User;

public interface MenuService {

	List<Menu> findAll();

	List<Menu> findByParentMenuIsNull();

	void save(Menu model);

	List<Menu> findByUser(User user);

}
