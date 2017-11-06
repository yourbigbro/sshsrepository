package com.itheima.realm;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import com.itheima.dao.PermissionDao;
import com.itheima.dao.RoleDao;
import com.itheima.dao.UserDao;
import com.itheima.system.Permission;
import com.itheima.system.Role;
import com.itheima.system.User;

public class CustomRealm extends AuthorizingRealm{

	@Autowired
	private UserDao userDao;
	//@Autowired
	//private PermissionDao permissionDao;
	@Autowired
	private RoleDao roleDao;

	/**
	 * 授权(第十二天内容)
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		
		//将下面的死数据注释掉改成动态数据
		
		//info.addStringPermission("standard-list");
		//角色授权
		//info.addRole("standard");
		//权限授权(只有他是常用的)
		//info.addStringPermission("courier");
		
		//第一步是得到用户对象，因为用户对象里面有角色和权限
		
		//下面的得到用户的方法是错误的，因为这不是action，所以不能用下面的方法获取session
		//User user=(User)ServletActionContext.getRequest().getSession().getAttribute("user");
		//获得user的第一种方法
		Subject subject = SecurityUtils.getSubject();
		User user=(User)subject.getPrincipal();
		//获得user的第二种方法
		//User user = (User)principals.getPrimaryPrincipal();
		//分别从user中获得角色对象和权限对象并进行赋值
		String username = user.getUsername();
		
		//动态授权的第一种方法(比较麻烦，所以用得少)
		/*if(username.equals("admin")){
			//用户名为admin的用户有所有的角色和权限
			List<Permission> findAll = permissionDao.findAll();
			for (Permission permission : findAll) {
				//这个keyword字段应该是专门用来设计成设置权限和角色的，是shiro的规定
				info.addStringPermission(permission.getKeyword());
			}
			
			List<Role> findAll2 = roleDao.findAll();
			for (Role role : findAll2) {
				info.addRole(role.getKeyword());
			}
		//假如不是admin超级管理员用户的话
		//注意，不管是角色还是权限，里面都有用户对象user
		}else {
			
			//注意，根据哪个对象查找就传入哪个对象的id
			List<Role> listRole=roleDao.findByUser(user.getId());
			for (Role role : listRole) {
				info.addRole(role.getKeyword());
			}
			
			List<Permission> listPermission=permissionDao.findByUser(user.getId());
			for (Permission permission : listPermission) {
				info.addStringPermission(permission.getKeyword());
			}
		}*/
		
		//动态授权的第二种方法，比较简单，用的多。是从用户中获得角色，再从角色中获得权限
		Set<Role> roles=null;
		if(user.getUsername().equals("admin")){
			List<Role> roleList = roleDao.findAll();
			//注意将list集合变成set集合的方法
			roles=new HashSet<>(roleList);
		}else {
			roles = user.getRoles();
		}
		//利用得到的role对象集合
		for (Role role : roles) {
			
			info.addRole(role.getKeyword());
			Set<Permission> permissions = role.getPermissions();
			
			for (Permission permission : permissions) {
				
				info.addStringPermission(permission.getKeyword());
			}
		}
		
		//返回的是封装了信息的对象
		return info;
	}

	/**
	 * 认证(项目第十一天的内容)
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		//获取到封装用户名和密码的对象
		UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken)token;
		//判断当前输入的用户名是否存在
		if(StringUtils.isNotBlank(usernamePasswordToken.getUsername())){
			//通用用户名查询用户
			User user = userDao.findByUsername(usernamePasswordToken.getUsername());
			if(user!=null){
				//principal可以随便放，但是一般我们放置当前登录的用户对象，因为这个参数可以在我们项目任意的地方获取到。
				//credentials放置我们数据库中的密码
				//realmName当前realm的名字
				//输入的账号没问题
				AuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), this.getName());
				//返回的也是对象
				return info;
			}else{
				return null;
			}
		}else{
			return null;
		}
	}

}
