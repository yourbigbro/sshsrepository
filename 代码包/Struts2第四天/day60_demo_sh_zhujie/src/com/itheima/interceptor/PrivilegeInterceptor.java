package com.itheima.interceptor;

import com.itheima.domain.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
//创建拦截器类
public class PrivilegeInterceptor extends MethodFilterInterceptor {

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		//判断session中是否有用户信息
		User  user=(User)ActionContext.getContext().getSession().get("user");
		System.out.println("经过了拦截器");
		if(user!=null){
			//invocation是上面传进来的参数
			//相当于放行
			return invocation.invoke();
		}else {
			//转发到登录页面
			return "login";
		}
		
	}

}
