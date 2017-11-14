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
	//筛选出今天过生日的user对象（用户）
	public List<User> sendMail() throws SQLException {
		DataSource ds = C3P0Utils.getDataSource();
		QueryRunner qr = new QueryRunner(ds);//传入链接池做参数
		String sql="select * from user where birthday like ?";
		String timeStr="%-"+DateUtils.getCurrentMonth()+"-"+DateUtils.getCurrentDay();
		List<User> query = qr.query(sql, new BeanListHandler<User>(User.class), timeStr);
		return query;
	}

}
