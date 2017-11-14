package com.itheima.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.dao.MenuDao;
import com.itheima.dao.PermissionDao;
import com.itheima.dao.RoleDao;
import com.itheima.service.RoleService;
import com.itheima.system.Menu;
import com.itheima.system.Permission;
import com.itheima.system.Role;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDao roleDao;
	@Autowired
	private PermissionDao permissionDao;
	@Autowired
	private MenuDao menuDao;

	//该方法用于找出所有的角色并显示在前台页面的列表中
	@Override
	public List<Role> findAll() {
		return roleDao.findAll();
	}

	//该方法用于保存新添加的角色
	@Override
	public void save(Role model) {
		roleDao.save(model);
		
	}

	//该方法用于给角色关联权限和菜单，是业务层service做的事情
	@Override
	public void saveRole(Role model, Integer[] permissionIds, String functionIds) {

		//将model对象持久化(也就是保存到数据库中)
				roleDao.save(model);
				//model对象持久化之后就可以保存对象(例如多对多的集合)。原理是hibernate一级缓存。
				//将前台传来的字符串functionIds变成数组的形式后进行遍历并循环添加菜单
				//这个split()函数当没有,分隔符的时候就会返回一个长度为一的数组，并不会报错
				String[] split = functionIds.split(",");
				for (String string : split) {
					//用菜单id查询出相应的菜单对象并添加到角色对象的菜单集合属性
					Menu menu = menuDao.findOne(Integer.parseInt(string));
					//将查询出来的menu对象放入集合
					model.getMenus().add(menu);
				}
				
				//遍历数组循环添加权限
				for (Integer permid : permissionIds) {
					
					//利用权限的id查询得到权限对象
					Permission permission=permissionDao.findOne(permid);
					//将查询到的权限对象赋给model对象做集合的元素
					model.getPermissions().add(permission);
				}
				
	}
}
