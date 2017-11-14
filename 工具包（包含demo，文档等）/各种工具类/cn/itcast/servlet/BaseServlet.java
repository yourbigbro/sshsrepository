package cn.itcast.servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 这个类只是用来被继承的
 * @author cxf
 *
 */
public abstract class BaseServlet extends HttpServlet {
	/**
	 * 让这个方法处理两种业务：登录和注册
	 * 要求：请求时必须传递一个参数，来说明业务类型！
	 */
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/**
		 * 处理请求中POST编码
		 * 处理响应编码问题
		 */
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		/*
		 * 获取业务类型
		 */
		String methodName = request.getParameter("method");
		if(methodName == null || methodName.trim().isEmpty()) {
			throw new RuntimeException("您必须传递要调用的方法名称，通过method参数来完成！");
		}
		/*
		 * 得到方法名称之后，通过反射来调用方法
		 * 1. 得到Class
		 * 2. 得到Method
		 * 3. 调用invoke()
		 */
		
		/*
		 * 得到Method
		 */
		Class c = this.getClass();
		Method method = null;
		try {
			method = c.getMethod(methodName,
					HttpServletRequest.class, HttpServletResponse.class);
		} catch (NoSuchMethodException e) {
			throw new RuntimeException("您要调用的方法：" + methodName + "不存在！", e);
		}
		
		/*
		 * 调用method的invoke()
		 */
		//对象.方法(参数)
		//method.invoke(对象, 参数)
		try {
			String path = (String)method.invoke(this, request, response);
			if(path == null || path.trim().isEmpty()) {//如果请求处理方法返回的是null或空，那么表示不重定向也不转发
				return;
			}
			/*
			 * path就是login或regist方法返回的是字符串
			 */
			/*
			 * path中有前缀，我们使用冒号分隔字符串，得到前缀和后缀
			 * 其中前缀说明了转发还是重定向，后缀说明了要转发或重定向的路径
			 * 如果没有提前缀，表示转发
			 */
			int index = path.indexOf(":");
			if(index == -1) {//如果没有冒号，说明没有前缀，那么就是转发
				request.getRequestDispatcher(path).forward(request, response);
			} else {
				String s1 = path.substring(0, index);//前缀
				String s2 = path.substring(index + 1);//后缀
				if(s1.equalsIgnoreCase("f")) {//前缀是f，那么说明要转发
					request.getRequestDispatcher(s2).forward(request, response);	
				} else if(s1.equalsIgnoreCase("r")) {//前缀是r，那么说明要重定向
					if(s2.toLowerCase().startsWith("http://")) {//是其他的项目
						response.sendRedirect(s2);
					} else {//本项目内页面
						response.sendRedirect(request.getContextPath() + s2);
					}
				} else {//抛出异常，说明前缀是错误的！
					throw new RuntimeException("你指定的操作：" + s1 + ", 当前还不支持！");
				}
			}
			
		} catch (Exception e) {
			throw new RuntimeException("您调用的方法：" + methodName + "抛出了异常！", e);
		}
		
		
//		if(methodName.equals("login")) {
//			login(request, response);
//		} else if(methodName.equals("regist")) {
//			regist(request,response);
//		}
	}
}
