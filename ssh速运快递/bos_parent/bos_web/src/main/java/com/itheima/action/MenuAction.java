package com.itheima.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;

import com.itheima.service.MenuService;
import com.itheima.system.Menu;
import com.itheima.system.User;
import com.itheima.utils.action.common.CommonAction;

public class MenuAction extends CommonAction<Menu>{

	@Autowired
	private MenuService menuService;
	
	//该方法用于查询menu列表并显示在前台页面中。只需要查询数据库即可
	@Action("menuList")
	public String menuList(){
		
		List<Menu> list=menuService.findAll();
		//并不是不用就可以不考虑懒加载的问题。即使页面不用，只要到了转换成json数据这一步懒加载就会报错
		this.java2json(list, new String[]{"parentMenu","roles"});
		//回写信息就不用了，因为CommonAction父类里面已经写好了
		return NONE;
	}
	
	
	//该方法用于查询没有父亲(也就是parentMenu)的数据行
	@Action("menuListWithoutParent")
	public String menuListWithoutParent(){
		
		List<Menu> list=menuService.findByParentMenuIsNull();
		this.java2json(list, new String[]{"parentMenu","roles"});
		return NONE;
	}
	
	//该方法用于保存新的Menu对象
	@Action("menu_save")
	public String menu_save(){
		menuService.save(model);
		return NONE;
	}
	
	//该方法用于根据不同的用户来加载基本功能菜单
	@Action("findMenu")
	public String findMenu(){
		
		//获取session中的用户对象
		User user=(User)ServletActionContext.getRequest().getSession().getAttribute("user");
		//只要是跟用户有关的都要判断是否是超级管理员
		if(user.getUsername().equals("admin")){
			//是超级管理员的话就获得数据库中所有的menu对象
			List<Menu> list = menuService.findAll();
			//注意，既然利用了父节点就要排除子节点的相关内容。连children也要排除，尽管它只有一个get方法而并没有成员变量
			this.java2json(list, new String[]{"roles","childrenMenus","children","parentMenu"});
		}else {
			//不是超级管理员的话就根据用户对象user(或者说user的id)从menu表中获得它具有的menu对象
			List<Menu> list = menuService.findByUser(user);
			this.java2json(list, new String[]{"roles","childrenMenus","children","parentMenu"});
		}
		return NONE;
	}
}
