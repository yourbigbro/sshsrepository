package com.itheima;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class App extends HttpServlet{

	private static final long serialVersionUID = 1L;
	@Override
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException{
		System.out.println("get请求即将执行");
		String eq = req.getParameter("username");
		String es = req.getParameter("password");
		Service service=new Service();
		String xiaoxi = null;
		try {
			xiaoxi = service.dengLu(eq,es);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//调用service层的方法
		System.out.println(xiaoxi);
		res.setContentType("text/html;charset=utf8");
		res.getWriter().println(xiaoxi);
		}
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException{
		System.out.println("post请求即将执行");
		String eq = req.getParameter("username");
		String es = req.getParameter("password");
		Service service=new Service();
		String xiaoxi = null;
		try {
			xiaoxi = service.dengLu(eq,es);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//调用service层的方法
		System.out.println(xiaoxi);
		res.setContentType("text/html;charset=utf8");
		res.getWriter().println("呵呵");
	}
	
}
	

