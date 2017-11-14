package dao;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import domain.User;
import utils.C3P0Utils;

public class AjaxDao {

	public User checkUsername(String value) {
		String sql="select * from word where word=? or pinyin=?";
		Object[] params={value,value};
		DataSource ds = C3P0Utils.getDataSource();
		QueryRunner qr = new QueryRunner(ds);
		User user;
		try {
			user = qr.query(sql, new BeanHandler<User>(User.class),params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return user;//∑µªÿ∂‘œÛ
	}

}
