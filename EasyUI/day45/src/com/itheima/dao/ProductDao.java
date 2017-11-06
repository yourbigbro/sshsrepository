package com.itheima.dao;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.itheima.domain.Product;
import com.itheima.utils.C3P0Utils;

public class ProductDao {
	//获得总的记录数
	public int getTotal() {
		String sql="select count(*) from product";
		DataSource ds = C3P0Utils.getDataSource();
		QueryRunner qr = new QueryRunner(ds);
		int total;
		try {
			total = ((Long) qr.query(sql, new ScalarHandler())).intValue();//注意类型转换
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return total;
	}
	//获得该页的对象集合
	public List<Product> getOnePageProducts(int pageNumber, int pageSize) {
		DataSource ds = C3P0Utils.getDataSource();
		QueryRunner qr = new QueryRunner(ds);
		String sql="select * from product limit ?,?";
		List<Product> ppProducts;
		//数组的两个参数分别是当页的起始索引和页的大小
		Object[] params={(pageNumber-1)*pageSize,pageSize};
		try {
			ppProducts = qr.query(sql, new BeanListHandler<>(Product.class),params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return ppProducts;
	}

}
