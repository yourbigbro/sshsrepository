package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.Service;

public class Servlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	@Override
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException{
		Service ser = new Service();
		try {
			ser.chaXun(req,res);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("²Ù×÷Ê§°Ü");
			e.printStackTrace();
		}
	}
	@Override
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException{
		doGet(req, res);
	}
}
