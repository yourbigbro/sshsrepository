package com.itheima;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

public class Dao {
	//queryName������������������Ϊquery�������������ӡ�
	public  Account queryName(String name) throws SQLException{
		//QueryRunner�ڷ�����ÿ���������涼��һ����
		QueryRunner qr = new QueryRunner();//QueryRunner���ڳ־ò㣬����Ϊ�����������ݿ⡣���������ʱ���ô������ӳض���ֻ���������query�������洫�����ӳ��еľ������ӣ���Ϊ�����ǹ̶��ģ����������������ӡ�
		Connection con=ThreadLocalUtils.getConn();//�������뿪�������������һ�����ӣ�����Ŀ�Ĳ����ٴζ���һ�����ӣ���������һ��������ThreadLocal�е����ӳ��е����ӡ�
		System.out.println(con+"��");
		String sql="select * from zhuanzhang where aname=?";//����һ��Ҫ����query�����������������������sql�ַ�������Ļ��ͻᱻ�����ַ��������Ǳ�����
		Account que = qr.query(con, sql, new BeanHandler<Account>(Account.class),name);//�������ӡ�
		//ThreadLocalUtils.commit();�����ύ����Ӧ�÷���service�㣨��ҵ��㣩����ʹ�á�
	
		return que;
	}
	//updateMoney������������������Ϊquery�������������ӡ�
    public  void updateMoney(String name,Double money) throws SQLException{
    	QueryRunner qr = new QueryRunner();//���������ʱ���ô������ӳض���ֻ���������query�������洫�����ӳ��еľ������ӣ���Ϊ�����ǹ̶��ģ����������������ӡ�
		Connection con=ThreadLocalUtils.getConn();//�������뿪�������������һ�����ӣ�����Ŀ�Ĳ����ٴζ���һ�����ӣ���������һ��������ThreadLocal�е����ӳ��е����ӡ�
		String sql="update zhuanzhang set amoney=? where aname=?";
	    qr.update(con, sql,money,name);//����һ��Ҫ����query�����������������������sql�ַ�������Ļ��ͻᱻ�����ַ��������Ǳ������������ӡ�
		//ThreadLocalUtils.commit();�����ύ����Ӧ�÷���service�㣨��ҵ��㣩����ʹ�á�
	    }
}
