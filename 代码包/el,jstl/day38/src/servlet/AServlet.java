package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.Service;
@WebServlet(name="AServlet",urlPatterns="/servlet/AServlet")//浏览器或者使用它的页面要输入项目名加urlPatterns的值。该行无法覆盖web.xml中的配置文件。
public class AServlet extends HttpServlet {//有get和post方法的地方HttpServlet，否则他就不是一个Servlet
	private static final long serialVersionUID = 1L;
       
	@Override
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException{
		Service ser = new Service();
		try {
			ser.chaXun(req,res);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("操作失败");
			e.printStackTrace();
		}
	}
	@Override
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException{
		doGet(req, res);
	}

}
