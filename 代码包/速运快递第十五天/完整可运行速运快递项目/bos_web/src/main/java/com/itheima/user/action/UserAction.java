package com.itheima.user.action;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import com.itheima.system.User;
import com.itheima.utils.action.common.CommonAction;


public class UserAction extends CommonAction<User>{

	private String checkcode;
	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}
	/**
	 * 认证操作(也就是登录)
	 * @return
	 */
	@Action(value="user_login", results={@Result(name="success", type="redirect", location="/index.html"),
			@Result(name="input", type="redirect", location="/login.html")})
	public String login(){
		//获取session中的验证码
		String checkcodeSeesion = (String) ServletActionContext.getRequest().getSession().getAttribute("key");
		if(StringUtils.isNotBlank(checkcode) && checkcode.equals(checkcodeSeesion)){
			//获取到subject当前对象
			Subject subject = SecurityUtils.getSubject();
			//token是给认证封装参数
			AuthenticationToken token = new UsernamePasswordToken(model.getUsername(), model.getPassword());
			try {
				//调用当前对象的login方法
				subject.login(token);
				//获取到当前登录的用户信息放到session中
				User user = (User) subject.getPrincipal();
				ServletActionContext.getRequest().getSession().setAttribute("user", user);
			} catch (Exception e) {
				e.printStackTrace();
				return INPUT;
			}
		}else{
			return INPUT;
		}
		return SUCCESS;
	}
	
	//退出登录(注销登录)
	@Action(value="logout", results={@Result(name="success", type="redirect", location="/index.html")})
	public String logout(){
		
		Subject subject = SecurityUtils.getSubject();
		//用shiro注解的形式进行注销登录
		subject.logout();
		return SUCCESS;
	}
}
