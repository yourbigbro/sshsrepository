package servlet;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(name="CuoWu",urlPatterns="/servlet/CuoWu")//注解，第一个参数随便写，第二个参数表示访问哪个路径可以得到他。
public class CuoWu extends HttpServlet{

	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException{
		String attr = (String)req.getAttribute("信息");
		res.setContentType("text/html;charset=utf-8");
		res.getWriter().write(attr);
	}
}
