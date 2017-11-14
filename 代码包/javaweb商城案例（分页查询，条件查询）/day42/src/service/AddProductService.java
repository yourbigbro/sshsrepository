package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.AddProductDao;
import dao.ProductDao;
import domain.Category;
import domain.Product;
import utils.C3P0Utils;

public class AddProductService {

	public List<Category> getCheck() throws SQLException {
		AddProductDao apd=new AddProductDao();
		List<Category> categorys=apd.getCheck();
		return categorys;
	}
	//增加商品
	public void addProduct(Product product) throws SQLException {
		AddProductDao apd=new AddProductDao();
		apd.addProduct(product);
		
	}
	//删除多个商品
	public void delete(String[] pv) {
		// 进行事务操作
		Connection conn = C3P0Utils.getConnection();
		AddProductDao apd = new AddProductDao();
		apd.delete(conn,pv);
		try {
			conn.setAutoCommit(false);
			
		} catch (SQLException e) {
			try {
				conn.rollback();//回滚也会抛出异常
			} catch (SQLException e1) {
				throw new RuntimeException(e);
			}
		}
	}
	public void deletePid(String pa) {
		AddProductDao apd = new AddProductDao();
		Connection conn = C3P0Utils.getConnection();
		try {
			conn.setAutoCommit(false);
			if (pa!=null) {
				apd.deletePid(pa,conn);
			}
			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				throw new RuntimeException(e);
			}
		}
		
	}
	public List<Category> getCategory() {
		AddProductDao apd = new AddProductDao();
		List<Category> categories=apd.getCategory();
		return categories;
	}
	public Product getProduct(String pid) {
		AddProductDao aps = new AddProductDao();
		Product product=aps.getProduct(pid);
		return product;
	}
	public void change(Product pro) {
		AddProductDao apd = new AddProductDao();
		apd.change(pro);
	}
	public  List<Product> selectProduct(String describe, String selectcname) {
		AddProductDao apd = new AddProductDao();
		return apd.selectProduct(describe,selectcname);
	}
	public List<Product> getList(int startIndex, int pageSize) {
		AddProductDao apd = new AddProductDao();
		List<Product> data= apd.getList(startIndex,pageSize);
		return data;
	}


}
