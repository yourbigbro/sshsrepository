package utils;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class Dbutils {
	private static DataSource ds=new ComboPooledDataSource();
	public static DataSource getCon(){
		return ds;
	}
}
