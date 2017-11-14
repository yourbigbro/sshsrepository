package servlet;


import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import domain.Account;
import service.Aservice;


public class DengLu extends HttpServlet{

	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException{
		req.setCharacterEncoding("utf-8");
		Map<String, String[]> pm = req.getParameterMap();
		Account acc = new Account();
		try {
			BeanUtils.populate(acc, pm);
			acc.setHobby(Arrays.toString(req.getParameterValues("hobby")));//单独设置爱好数组
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Aservice aser = new Aservice();
		try {
			aser.denglu(acc,req,res);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException{
		doGet(req, res);
	}
}
