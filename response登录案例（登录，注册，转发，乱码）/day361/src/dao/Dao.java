package dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import domain.Account;
import utils.Dbutils;

public class Dao {
	public void chaXun(String ausername,String apassword,String hobby,HttpServletRequest req,HttpServletResponse res) throws SQLException, ServletException, IOException{
		if(ausername==""||apassword==""||hobby==""){
			res.setContentType("text/html;charset=utf-8");
			res.getWriter().write("��Ŀ����Ϊ��");
			return;
		}
		DataSource ds = Dbutils.getCon();
		QueryRunner qr = new QueryRunner(ds);
		String sql="select * from xinxi where ausername=? and apassword=? and hobby=?";
		Account qu = qr.query(sql, new BeanHandler<Account>(Account.class), ausername,apassword,hobby.toString());
		if(qu==null){
			req.setAttribute("��Ϣ", "���������Ϣ����");//ת����Ϣ
			RequestDispatcher rd = req.getRequestDispatcher("/servlet/CuoWu");
			rd.forward(req, res);
		}else{
			res.setContentType("text/html;charset=utf-8");//ע���÷ֺż�������Ƕ���
			res.getWriter().write("��½�ɹ�");
		}
	}
	public void zhuCe(String ausername,String apassword,String hobby,HttpServletRequest req,HttpServletResponse res) throws SQLException, IOException{
		if(ausername==""||apassword==""||hobby==""){
			res.setContentType("text/html;charset=utf-8");
			res.getWriter().write("��Ŀ����Ϊ��");
			return;
		}
		DataSource ds = Dbutils.getCon();
		QueryRunner qr = new QueryRunner(ds);
		String sql1="select * from xinxi where ausername=? and apassword=? and hobby=?";////////////
		Account qu1 = qr.query(sql1, new BeanHandler<Account>(Account.class), ausername,apassword,hobby);
		if(qu1!=null){
			res.setContentType("text/html;charset=utf-8");
			res.getWriter().write("�ѱ�ע�ᣬ����ע��һ��");
		}else{
			String sql="insert into xinxi (ausername,apassword,hobby) values (?,?,?)";
			int qu = qr.update(sql, ausername,apassword,hobby);
			res.setContentType("text/html;charset=utf-8");
			res.getWriter().write("ע��ɹ�");
		}
		
		
	}
}
