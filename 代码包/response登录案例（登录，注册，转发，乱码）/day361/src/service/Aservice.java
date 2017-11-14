package service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Dao;
import domain.Account;

public class Aservice {
	public void denglu(Account acc,HttpServletRequest req,HttpServletResponse res) throws SQLException, ServletException, IOException{
		String ausername = acc.getAusername();
		String apassword = acc.getApassword();
		String hobby = acc.getHobby();
		Dao dao = new Dao();
		dao.chaXun(ausername,apassword,hobby,req,res);
	}
	public void zengjia(Account acc,HttpServletRequest req,HttpServletResponse res) throws SQLException, ServletException, IOException{
		String ausername = acc.getAusername();
		String apassword = acc.getApassword();
		String hobby = acc.getHobby();
		Dao dao = new Dao();
		dao.zhuCe(ausername,apassword,hobby,req,res);
	}
}
