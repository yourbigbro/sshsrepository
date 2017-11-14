package com.itheima.day49.user.utils;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itheima.day49.user.domain.User;

public class PriFilter implements Filter {

    private static final String HttpServletRequest = null;

	public PriFilter() {
        
    }

	public void destroy() {
		
	}
	//有用的方法
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//首先进行强制转换
		HttpServletRequest req=(HttpServletRequest)request;
		HttpServletResponse resp=(HttpServletResponse)response;
		//从域中获得session，也就是没登陆的时候不让访问页面
		User user = (User)req.getSession().getAttribute("user");
		if(user!=null){
			chain.doFilter(request, response);
		}else{
			req.setAttribute("msg", "没登陆就想看？给老子登录");
			req.getRequestDispatcher("/msg.jsp").forward(request, response);//此处的路径最好是加上斜杠
		}
		
	}

	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
