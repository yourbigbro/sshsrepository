package com.itheima.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;//注意导包别导错了

import com.itheima.domain.Account;

public class MyRowMapper implements RowMapper<Account>{
	
	//实现接口的方法
	//注意返回值是接口的泛型类，因此显而易见他是封装对象用的
	@Override
	public Account mapRow(ResultSet rs, int rn) throws SQLException {
		//参数rn是结果集中的索引，有多少个索引就会从产生多少个account对象，他们组成一个结果集List<Account>
		Account account = new Account();
		account.setId(rs.getInt("id"));
		account.setName(rs.getString("name"));
		account.setMoney(rs.getFloat("money"));
		return account;
	}

}
