package dao;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import domain.User;
import utils.C3P0Utils;

public class AjaxDao {

	public List<User> checkUsername(String value) {
		String sql="select * from word where word like ? or pinyin like ? limit 5";//ע���������ӷ�ҳ
		Object[] params={"%"+value+"%","%"+value+"%"};
		DataSource ds = C3P0Utils.getDataSource();
		QueryRunner qr = new QueryRunner(ds);
		List<User> user;
		try {
			user =  qr.query(sql, new BeanListHandler<User>(User.class),params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return user;//���ض���
	}

}
