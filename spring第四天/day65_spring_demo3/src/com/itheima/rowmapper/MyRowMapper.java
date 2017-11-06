package com.itheima.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.itheima.domain.Account;

public class MyRowMapper implements RowMapper<Account>{

	@Override
	public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
//		System.out.println(rowNum); //结果集中行的索引
		Account a = new Account();
		a.setId(rs.getInt("id"));
		a.setName(rs.getString("name"));
		a.setMoney(rs.getFloat("money"));
		return a;
	}

}
