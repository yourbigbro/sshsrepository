package com.itheima.day49.user.utils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;//注意导的是这个包

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//父类（提取类）
public class BaseServlet extends HttpServlet {//即使继承了httpservlet类也不会自动写出里面的方法
	@Override
	public void service(HttpServletRequest request,HttpServletResponse response){
		//该方法里面不需要将servletrequest强制转换成httpservletrequest类型，那是过滤器里面干的事情
		String type = request.getParameter("type");//方法名
		//检测是否传来了属性值
		if(type!=null){//传了属性值
			Class clazz = this.getClass();//获得类对象。这个this指的是UserServlet的实例对象
			Method method;//定义变量
			try {
				method = clazz.getMethod(type,HttpServletRequest.class,HttpServletResponse.class);
			} catch (NoSuchMethodException e) {
				System.out.println("参数传输的有问题");
				throw new RuntimeException(e);
			} catch (SecurityException e) {
				throw new RuntimeException(e);
			}
			String str;//定义方法返回值变量
			try {
				str = (String)method.invoke(this, request,response);//执行方法返回的值，是页面地址，用于根据不同的返回值通向不同页面
			} catch (IllegalAccessException e) {
				throw new RuntimeException(e);
			} catch (IllegalArgumentException e) {
				throw new RuntimeException(e);
			} catch (InvocationTargetException e) {
				throw new RuntimeException(e);
			}
			
			try {
				if(str!=null){
					request.getRequestDispatcher(str).forward(request, response);//转发，由于后台页面相对于项目名，所以路径不用加项目名
				}else {//没有返回值的情况
					System.out.println("返回值为null，为异步加载而不是转发");
				}
			} catch (ServletException e) {
				throw new RuntimeException(e);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			
		}else{//未传属性值
			System.out.println("你忘记写属性值了");
		}
	}
}
