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
		Cookie[] cookies = req.getCookies();//�õ�cookie
		String str="ʱ��";
		String str1=URLEncoder.encode(str, "utf-8");//����
		boolean flag=false;
		if(cookies!=null){
			System.out.println("qqq");
			int count=0;
			for (Cookie cookie1 : cookies) {
				count++;
				System.out.println(URLDecoder.decode(cookie1.getName(),"utf-8"));//��ӡcookie������Ϊ��jsp�ļ�������cookie�����б�����JSESSIONID
				if(URLDecoder.decode(cookie1.getName(),"utf-8").equals(str)){//ע��Ҫ��equals�����õȺţ���Ϊ���߱Ƚϵ����ڴ档ע��Ƚ�֮ǰ�ı���������⡣
					res.getWriter().write("<h5 style='color:green'>��ǰʱ��Ϊ��"+cookie1.getValue()+"</h5>");
					flag=true;
					break;
				}
			}
			//System.out.println("cookie�ĸ����ǣ�"+count);
		}
		
		if(flag==false){
			res.getWriter().write("<h5 style='color:green'>��ӭ�״�����</h5>");//����ʵ��֮ǰ��Ҫ�����������棬�����޷������״����ã�һֱ������ʾ��ǰʱ��
		}
		Cookie cookie=new Cookie(str1, new Date().toLocaleString());//�����µ�cookie
		cookie.setMaxAge(60*60*24*7);
		res.addCookie(cookie);//����cookie
	}
	@Override
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException{
		doGet(req, res);
	}
}
