package dao;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import domain.User;
import utils.C3P0Utils;
import utils.DateUtils;

public class SendMailDao {
	//ɸѡ����������յ�user�����û���
	public List<User> sendMail() throws SQLException {
		DataSource ds = C3P0Utils.getDataSource();
		QueryRunner qr = new QueryRunner(ds);//�������ӳ�������
		String sql="select * from user where birthday like ?";
		String timeStr="%-"+DateUtils.getCurrentMonth()+"-"+DateUtils.getCurrentDay();
		List<User> query = qr.query(sql, new BeanListHandler<User>(User.class), timeStr);
		return query;
	}

}
