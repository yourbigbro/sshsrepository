package service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Dao;
import domain.Product;

public class Service {//取名字要取特殊的名字，这种名字既不能见名知义而且是关键字，容易引发eclipse内部的bug
	public void chaXun(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException, SQLException{
		Dao dao = new Dao();
		List<Product> products =dao.query();
		req.getSession().setAttribute("products", products);//该种方法可以重定向
		//req.setAttribute("products", products);//该种方法只可以转发不可以重定向
		req.getRequestDispatcher("/html/chaxun.jsp").forward(req, res);
	}
	
}
