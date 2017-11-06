package com.itheima;

import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3p0Utils {
	public static DataSource ds=new ComboPooledDataSource();
	public static DataSource getCon() throws SQLException {
		return ds;
	}
}
