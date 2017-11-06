package app;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.Service;

public class DengLuServlet extends HttpServlet {
	
	
	private static final long serialVersionUID = 1L;
	Service ser=new Service();
	ServletContext servletcontext;
	@Override
	public void init(){
		servletcontext=this.getServletContext();
		 int count =0;
		servletcontext.setAttribute("count",count);
	}
	
	@Override
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException{
		
		if(req.getHeader("Referer")!="http://localhost:8080/day35/denglu.jsp"){//防盗链，防止不允许的页面请求这个.java后台文件
			res.getWriter().write("fuck you");
		}
		
		String pa1 = req.getParameter("username");//获得前台传来的表单信息
		String pa2 = req.getParameter("password");
		String xinxi = null;
		res.setCharacterEncoding("utf-8");//防止传给前台的中文信息出现乱码（问号）
		res.setContentType("text/html;charset=utf-8");
		try {
			xinxi = ser.chaXun(pa1,pa2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(xinxi=="查询失败"){
			res.getWriter().write("<h3 style='color:blue;'>用户名或者密码出错</h3>");//给前台传送信息
			res.setHeader("Refresh","5;URL=http://localhost:8080/day35/denglu.jsp");//注意路径只有项目名称和页面名称
		}else{
			int count=(int) servletcontext.getAttribute("count");
			count++;
			servletcontext.setAttribute("count", count);
			res.getWriter().write("<h3 style='color:bule'>登陆成功，您是第"+count+"位登陆者</h3>");
			/*res.setHeader("Refresh","5;URL=http://localhost:8080/day35/xiazai.html");//这是刷新，不是重定向
*/			//重定向。重定向和刷新都能跳转页面，选一种即可。后者可以设置时间前者不能。
			res.setStatus(302);
			res.setHeader("Location","http://localhost:8080/day35/xiazai.html");
		}
	}
	@Override
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException{
		doGet(req, res);//两种方式方法相同，所以直接调用
	}
}
