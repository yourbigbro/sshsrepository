package Utils;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3p0Utils {
	private static DataSource ds=new ComboPooledDataSource();
	public static DataSource getDataSource(){
		return ds;
	}
}
