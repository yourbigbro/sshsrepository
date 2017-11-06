package dao;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import Utils.C3p0Utils;
import domain.Product;

public class Dao {
	public List<Product> query() throws SQLException{
		DataSource ds = C3p0Utils.getDataSource();
		String sql="select * from message";
		QueryRunner qr = new QueryRunner(ds);
		List<Product> query = qr.query(sql, new BeanListHandler<Product>(Product.class));
		return query;
	}
}
