package servlet;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(name="LoginServlet",urlPatterns="/servlet/LoginServlet")
public class LoginServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	@Override
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException{
		req.setCharacterEncoding("utf-8");
		res.setContentType("text/html;charset=utf-8");
		String vc = (String)req.getSession().getAttribute("vc");
		String verifycode = req.getParameter("verifycode");
		if(vc.equalsIgnoreCase(verifycode)){
			res.getWriter().write("<h5 style='color:green'>—€…Ò∫‹∫√</h5>");
		}else{
			res.getWriter().write("<h5 style='color:red'>ƒ„œπ∞°</h5>");
		}
	}
	
	@Override
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException{
		doGet(req, res);
	}
}
