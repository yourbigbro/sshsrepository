package servlet;


import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import domain.Account;
import service.Aservice;


public class ZhuCe extends HttpServlet{

	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException{
		Map<String, String[]> pm = req.getParameterMap();
		Set<String> keySet = pm.keySet();
		for (String str : keySet) {
			if(pm.get(str)==null){
				res.setContentType("text/html;charset=utf-8");
				res.getWriter().write("项目不能为空");
				break;
			}
		}
		
		req.setCharacterEncoding("utf-8");
		Account acc = new Account();
		try {
			BeanUtils.populate(acc, pm);
			acc.setHobby(Arrays.toString(pm.get("hobby")));//单独设置hobby
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Aservice aser = new Aservice();
		try {
			aser.zengjia(acc,req,res);//通往service层
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException{
		doGet(req, res);
	}
}
