package service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Dao;
import domain.Product;

public class Service {
	public void chaXun(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException, SQLException{
		Dao dao = new Dao();
		List<Product> products =dao.query();
		req.setAttribute("products", products);
		req.getRequestDispatcher("/html/chaxun.jsp").forward(req, res);
	}
	
}
