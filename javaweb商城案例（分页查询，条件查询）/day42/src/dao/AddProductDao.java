package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import domain.Category;
import domain.Product;
import utils.C3P0Utils;

public class AddProductDao {

	public List<Category> getCheck() throws SQLException {
		String sql="select * from category";
		DataSource ds = C3P0Utils.getDataSource();
		QueryRunner qr = new QueryRunner(ds);
		List<Category> query = qr.query(sql, new BeanListHandler<Category>(Category.class));
		return query;
	}

	public void addProduct(Product product) throws SQLException {
		String sql="insert into product values(?,?,?,?,?,?,?,?,?,?)";
		DataSource ds = C3P0Utils.getDataSource();
		QueryRunner qr = new QueryRunner(ds);
		Object[] params={product.getPid(),product.getPname(),product.getMarket_price(),product.getShop_price(),product.getPimage(),product.getPdate(),product.getIs_hot(),product.getPdesc(),product.getPflag(),product.getCategory().getCid()};
		qr.update(sql, params);
	}

	public void delete(Connection conn,String[] pv) {
		QueryRunner qr = new QueryRunner();
		String sql="delete from product where pname=?";
		for (String string : pv) {//逐个删除
			System.out.println(string);
			try {
				qr.update(conn,sql, string);//注意更新的时候参数中没有beanlisthandler，并且使用事务的时候有conn作为第一个参数
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}

	public void deletePid(String pa, Connection conn) {//其实他并不用传入connection，重新获取一个也行，因为源码中当有connection就会自动引用而不是创建
		QueryRunner qr = new QueryRunner();
		String sql="delete from product where pid=?";
		try {
			qr.update(conn, sql, pa);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Category> getCategory() {
		String sql="select * from category";
		DataSource ds = C3P0Utils.getDataSource();
		QueryRunner qr = new QueryRunner(ds);
		List<Category> query ;
		try {
			query = qr.query(sql, new BeanListHandler<Category>(Category.class));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return query;
	}

	public Product getProduct(String pid) {
		String sql="select * from product where pid=?";
		DataSource ds = C3P0Utils.getDataSource();
		QueryRunner qr = new QueryRunner(ds);
		Product query ;
		try {
			query =  qr.query(sql, new BeanHandler<Product>(Product.class),pid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return query;
	}

	public void change(Product pro) {
		String pid = pro.getPid();
		String is_hot = pro.getIs_hot();
		String pdesc = pro.getPdesc();
		String pimage = pro.getPimage();
		String pname = pro.getPname();
		String shop_price = pro.getShop_price();
		String sql="update product set is_hot =?,pdesc =?,pimage =?,pname =?,shop_price =? where pid=?";
		Object[] params={is_hot,pdesc,pimage,pname,shop_price,pid}; 
		Connection conn = C3P0Utils.getConnection();
		QueryRunner qr = new QueryRunner();
		try {
			qr.update(conn, sql, params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Product> selectProduct(String describe, String selectcname) {
		//String sql="select * from product where pdesc like ? and cid = ?";
		String sql="select * from product where 1=1";
		//拼接字符串
		StringBuilder sb = new StringBuilder(sql);
		if(describe!=null&&describe!=""){
			sb.append(" and pdesc like ? ");
		}
		if(selectcname!=null&&selectcname!=""){
			sb.append(" and cid = ?");
		}
		sql = sb.toString();
		DataSource ds = C3P0Utils.getDataSource();
		QueryRunner qr = new QueryRunner(ds);
		Object[] params={"%"+describe+"%",selectcname};
		System.out.println(describe+"  "+selectcname);
		List<Product> query;
		try {
			query = qr.query(sql, new BeanListHandler<Product>(Product.class),params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return query;
	}

	public List<Product> getList(int startIndex, int pageSize) {
		DataSource ds = C3P0Utils.getDataSource();
		QueryRunner qr = new QueryRunner(ds);
		String sql="select * from product limit ?,?";
		Object[] params={startIndex,pageSize};
		List<Product> data;
		try {
			data = qr.query(sql, new BeanListHandler<Product>(Product.class),params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return data;
	}


}
