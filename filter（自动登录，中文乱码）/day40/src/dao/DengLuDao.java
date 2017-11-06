package dao;

import java.sql.SQLException;

import javax.servlet.http.Cookie;
import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;


import domain.YongHu;
import utils.C3P0Utils;

public class DengLuDao {
	DataSource ds=C3P0Utils.getDataSource();
	QueryRunner qr=new QueryRunner(ds);
	private String sql="select * from user where username=? and password=?";
	
	
	public YongHu dengLuChaXun(String parameter, String parameter2) throws SQLException {
		Object[] param={parameter,parameter2};
		YongHu query = qr.query(sql, new BeanHandler<YongHu>(YongHu.class), param);
		return query;
	}
}
