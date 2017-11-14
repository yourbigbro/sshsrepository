package dao;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import domain.YongHu;
import utils.C3P0Utils;

public class ZhuCeDao {

	public boolean zhuCe(String parameter, String parameter2) {//返回true代表用户名已经存在，也就是注册失败
		boolean boo=false;
		DataSource ds = C3P0Utils.getDataSource();
		QueryRunner qr = new QueryRunner(ds);
		boo=jianCha(parameter,  qr);
		if(boo=true){//信息重复的话不能注册信息到数据库
			return boo;
		}
		try {
			chaRu(parameter, parameter2, qr, boo);
		} catch (SQLException e) {
			System.out.println("插入数据出错");
			e.printStackTrace();
		}
		return boo;
	}
	private boolean jianCha(String parameter,QueryRunner qr) {
		boolean flag=false;
		String sql="select * from user where username=? and password like %";
		YongHu query = null;
		try {
			query = qr.query(sql, new BeanHandler<YongHu>(YongHu.class),parameter);
		} catch (SQLException e) {
			System.out.println("查询出错");
			e.printStackTrace();
		}
		if(query!=null){
			flag=true;
		}
		return flag;
	}
	private void chaRu(String parameter, String parameter2,QueryRunner qr,boolean boo) throws SQLException{
		String sql="insert into user values(?,?)";
		qr.update(sql, parameter,parameter2);
	}
}
