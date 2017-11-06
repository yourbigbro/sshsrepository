package dao;

import java.sql.SQLException;
import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import c3p0.C3p0Utils;
import domain.Account;

public class Dao {
	public static Account query(String req,String res) throws SQLException{
		DataSource ds=C3p0Utils.getCon();
		QueryRunner qr=new QueryRunner(ds);
		String sql="select * from xinxi where ausername=? and apassword=?";
		Account qu = qr.query(sql, new BeanHandler<Account>(Account.class),req,res);
		return qu;
	}
	
	
}
