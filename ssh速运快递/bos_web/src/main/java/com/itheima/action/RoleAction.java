package com.itheima.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.itheima.service.PermissionService;
import com.itheima.service.RoleService;
import com.itheima.system.Permission;
import com.itheima.system.Role;
import com.itheima.utils.action.common.CommonAction;

public class RoleAction extends CommonAction<Role>{
	
	@Autowired
	private RoleService roleService;

	//该方法用于展示所有的角色role。他是不用跳转页面的，因为就是回到原来的页面
	@Action("listAllRole")
	public String listAllRole(){
		
		List<Role> list=roleService.findAll();
		this.java2json(list, new String[]{"users","permissions","menus"});
		return NONE;
	}
	
	//用属性驱动的方式获得前台传来的复选框选中项。前台可以传递数组或字符串
	//注意，属性内部是Integer，不用写成String。前台原来是String，估计是后台可以自动转换
	private Integer[] permissionIds;
	
	private String functionIds;
	
	public void setPermissionIds(Integer[] permissionIds) {
		this.permissionIds = permissionIds;
	}

	public void setFunctionIds(String functionIds) {
		this.functionIds = functionIds;
	}

	@Autowired
	private PermissionService permissionService;
	
	//该方法用于保存新添加的角色(保存完之后返回原来的页面以便进行重新展示更新之后的数据库)
	@Action(value="saveRole",results={@Result(name="success",type="redirect",location="/pages/system/role.html")})
	//虽然saveRole()方法里面包含的方法有参数，但是saveRole()方法是不能有参数的
	public String saveRole(){
		
		//业务逻辑必须交由service层来处理，因为涉及到事务。在action层处理既不合逻辑又无法进行事务操作
		roleService.saveRole(model,permissionIds,functionIds);
		
		return SUCCESS;
	}
}
