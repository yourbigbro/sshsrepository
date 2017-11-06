package dao;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import domain.YongHu;
import utils.C3P0Utils;

public class ZhuCeDao {

	public boolean zhuCe(String parameter, String parameter2) {//����true�����û����Ѿ����ڣ�Ҳ����ע��ʧ��
		boolean boo=false;
		DataSource ds = C3P0Utils.getDataSource();
		QueryRunner qr = new QueryRunner(ds);
		boo=jianCha(parameter,  qr);
		if(boo=true){//��Ϣ�ظ��Ļ�����ע����Ϣ�����ݿ�
			return boo;
		}
		try {
			chaRu(parameter, parameter2, qr, boo);
		} catch (SQLException e) {
			System.out.println("�������ݳ���");
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
			System.out.println("��ѯ����");
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
