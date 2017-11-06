package service;

import java.sql.SQLException;


import dao.Dao;
import domain.Account;

public class Service {
	public String chaXun(String req,String res) throws SQLException{
		String s="查询成功";
		Dao dao=new Dao();
		Account acc=dao.query(req,res);
		if(acc==null){
			s="查询失败";
		}
		return s;
	}
}
