package dao;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import domain.Product;
import utils.C3P0Utils;

public class ProductDao {

	public List<Product> showAllProduct() throws SQLException {
		String sql="select * from product";
		DataSource ds = C3P0Utils.getDataSource();
		QueryRunner qr = new QueryRunner(ds);
		List<Product> query = qr.query(sql, new BeanListHandler<Product>(Product.class));
		return query;
	}

}
