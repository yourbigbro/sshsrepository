package servlet;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(name="CuoWu",urlPatterns="/servlet/CuoWu")//ע�⣬��һ���������д���ڶ���������ʾ�����ĸ�·�����Եõ�����
public class CuoWu extends HttpServlet{

	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException{
		String attr = (String)req.getAttribute("��Ϣ");
		res.setContentType("text/html;charset=utf-8");
		res.getWriter().write(attr);
	}
}
