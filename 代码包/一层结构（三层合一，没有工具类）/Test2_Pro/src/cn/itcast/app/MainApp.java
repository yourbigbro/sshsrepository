package cn.itcast.app;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.itcast.domain.Product;
import cn.itcast.tools.JDBCUtils;



public class MainApp {

	public static void main(String[] args) throws Exception {
		String msg="成功修改数据库";
		//Connection conn=JDBCUtils.getConnection();//建立连接（这么做的话后面都可以引用该conn连接，而不用也不能再创建连接，否则不是一个连接。）
		Connection conn=null;//在这里设置成null的话后面就要对conn进行赋值
		//System.out.println(conn);
		QueryRunner qs = new QueryRunner();
		//建立连接
		//DataSource ds = JDBCUtils.getDataSource();
//		ThreadLocal<Connection> tl=new ThreadLocal<Connection>();
//		tl.set(conn);既然不分层那就没必要使用工具类里的ThreadLocal
		try {
			
			
			
//			try {
//				conn = JDBCUtils.getConnection();
//			} catch (Exception e) {
//				e.printStackTrace();
//				throw new RuntimeException("建立连接失败");
//			}
			//开启事务
			try {
//				conn = tl.get();
				conn=JDBCUtils.getConnection();
				System.out.println(conn);
				conn.setAutoCommit(false);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("开启事务失败");
			}
			String sql="select * from product";
			BeanListHandler<Product> bh = new BeanListHandler<Product>(Product.class);
			//获得对象
			List<Product> query = null ;
			try {
//				conn = tl.get();
				conn=JDBCUtils.getConnection();
				System.out.println(conn);
				query = qs.query(conn, sql, bh);
				
			} catch (Exception e1) {
				e1.printStackTrace();
				throw new RuntimeException("获得对象失败");
			}
			for (Product pro : query) {
				pro.setStock(pro.getStock()+20);
				String sql1="update product set discount=?, stock=? where id=?";
				if(pro.getStock()<60){
					pro.setDiscount(0);
					try {
						qs.update(conn, sql1);
					} catch (Exception e) {
						e.printStackTrace();
						throw new RuntimeException("更新行数据失败");
					}
				}else if(pro.getStock()<80&&pro.getStock()+20>=60){
					pro.setDiscount(9);
					try {
						qs.update(conn, sql1,pro.getDiscount(),pro.getStock(),pro.getId());
					} catch (Exception e) {
						e.printStackTrace();
						throw new RuntimeException("更新行数据失败");
					}
				}else if(pro.getStock()<100&&pro.getStock()+20>=80){
					pro.setDiscount(8);
					try {
						qs.update(conn, sql1,pro.getDiscount(),pro.getStock(),pro.getId());
					} catch (Exception e) {
						e.printStackTrace();
						throw new RuntimeException("更新行数据失败");
					}
				}else if(pro.getStock()==100){
					pro.setDiscount(7);
					try {
					} catch (Exception e) {
						e.printStackTrace();
						throw new RuntimeException("更新行数据失败");
					}
				}
				
			}
			
			//提交事务
			try {
//				conn = tl.get();    注意，将conn变为全局变量之后就不用每次都从ThreadLocal工具类中获得连接了
				conn=JDBCUtils.getConnection();
				conn.commit();
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("提交事务失败");
			}
			System.out.println(msg);
		} catch (Exception e) {
			
			//回滚事务
			try {
//				conn = tl.get();
				conn=JDBCUtils.getConnection();
				conn.rollback();
			} catch (Exception e3) {
				e3.printStackTrace();
				throw new RuntimeException("回滚事务失败");
			}
			msg="修改数据库失败";
			System.out.println(msg);
		}
		
		
		
	}
	
}
