package com.itheima;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

public class Dao {
	public Account chaXun(String username,String password) throws SQLException{
		QueryRunner qr=new QueryRunner(C3p0Utils.ds);
		String sql="select * from denglu where aname=? and apassword=?";
		Account acc = qr.query(sql, new BeanHandler<Account>(Account.class),username,password);
		return acc;
	}
	
}
