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
		String msg="�ɹ��޸����ݿ�";
		//Connection conn=JDBCUtils.getConnection();//�������ӣ���ô���Ļ����涼�������ø�conn���ӣ�������Ҳ�����ٴ������ӣ�������һ�����ӡ���
		Connection conn=null;//���������ó�null�Ļ������Ҫ��conn���и�ֵ
		//System.out.println(conn);
		QueryRunner qs = new QueryRunner();
		//��������
		//DataSource ds = JDBCUtils.getDataSource();
//		ThreadLocal<Connection> tl=new ThreadLocal<Connection>();
//		tl.set(conn);��Ȼ���ֲ��Ǿ�û��Ҫʹ�ù��������ThreadLocal
		try {
			
			
			
//			try {
//				conn = JDBCUtils.getConnection();
//			} catch (Exception e) {
//				e.printStackTrace();
//				throw new RuntimeException("��������ʧ��");
//			}
			//��������
			try {
//				conn = tl.get();
				conn=JDBCUtils.getConnection();
				System.out.println(conn);
				conn.setAutoCommit(false);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("��������ʧ��");
			}
			String sql="select * from product";
			BeanListHandler<Product> bh = new BeanListHandler<Product>(Product.class);
			//��ö���
			List<Product> query = null ;
			try {
//				conn = tl.get();
				conn=JDBCUtils.getConnection();
				System.out.println(conn);
				query = qs.query(conn, sql, bh);
				
			} catch (Exception e1) {
				e1.printStackTrace();
				throw new RuntimeException("��ö���ʧ��");
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
						throw new RuntimeException("����������ʧ��");
					}
				}else if(pro.getStock()<80&&pro.getStock()+20>=60){
					pro.setDiscount(9);
					try {
						qs.update(conn, sql1,pro.getDiscount(),pro.getStock(),pro.getId());
					} catch (Exception e) {
						e.printStackTrace();
						throw new RuntimeException("����������ʧ��");
					}
				}else if(pro.getStock()<100&&pro.getStock()+20>=80){
					pro.setDiscount(8);
					try {
						qs.update(conn, sql1,pro.getDiscount(),pro.getStock(),pro.getId());
					} catch (Exception e) {
						e.printStackTrace();
						throw new RuntimeException("����������ʧ��");
					}
				}else if(pro.getStock()==100){
					pro.setDiscount(7);
					try {
					} catch (Exception e) {
						e.printStackTrace();
						throw new RuntimeException("����������ʧ��");
					}
				}
				
			}
			
			//�ύ����
			try {
//				conn = tl.get();    ע�⣬��conn��Ϊȫ�ֱ���֮��Ͳ���ÿ�ζ���ThreadLocal�������л��������
				conn=JDBCUtils.getConnection();
				conn.commit();
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("�ύ����ʧ��");
			}
			System.out.println(msg);
		} catch (Exception e) {
			
			//�ع�����
			try {
//				conn = tl.get();
				conn=JDBCUtils.getConnection();
				conn.rollback();
			} catch (Exception e3) {
				e3.printStackTrace();
				throw new RuntimeException("�ع�����ʧ��");
			}
			msg="�޸����ݿ�ʧ��";
			System.out.println(msg);
		}
		
		
		
	}
	
}
