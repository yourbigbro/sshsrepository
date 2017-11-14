package servlet;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;

import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(name="CookieServlet",urlPatterns="/servlet/CookieServlet")
public class CookieServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("deprecation")
	@Override
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException{
		req.setCharacterEncoding("utf-8");
		res.setContentType("text/html;charset=utf-8");
		Cookie[] cookies = req.getCookies();//得到cookie
		String str="时间";
		String str1=URLEncoder.encode(str, "utf-8");//编码
		boolean flag=false;
		if(cookies!=null){
			System.out.println("qqq");
			int count=0;
			for (Cookie cookie1 : cookies) {
				count++;
				System.out.println(URLDecoder.decode(cookie1.getName(),"utf-8"));//打印cookie名，因为有jsp文件，所以cookie名字列表中有JSESSIONID
				if(URLDecoder.decode(cookie1.getName(),"utf-8").equals(str)){//注意要用equals不能用等号，因为后者比较的是内存。注意比较之前的编码解码问题。
					res.getWriter().write("<h5 style='color:green'>当前时间为："+cookie1.getValue()+"</h5>");
					flag=true;
					break;
				}
			}
			//System.out.println("cookie的个数是："+count);
		}
		
		if(flag==false){
			res.getWriter().write("<h5 style='color:green'>欢迎首次来访</h5>");//重新实验之前需要清除浏览器缓存，否则无法出现首次来访，一直都是显示当前时间
		}
		Cookie cookie=new Cookie(str1, new Date().toLocaleString());//设置新的cookie
		cookie.setMaxAge(60*60*24*7);
		res.addCookie(cookie);//设置cookie
	}
	@Override
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException{
		doGet(req, res);
	}
}
