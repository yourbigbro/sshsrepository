package cn.itcast.tools;

import java.sql.Connection;
import java.sql.SQLException;


import javax.sql.DataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JDBCUtils {
		private static ComboPooledDataSource ds=new ComboPooledDataSource();
		public static Connection con=null;
		
	public static DataSource getDataSource(){
		return ds;
	}
	public static Connection getConnection(){
		//Connection con;
		try {
			if(con==null){
			con = ds.getConnection();//从c3p0连接池建立连接
			//System.out.println(con);
			}
			return con;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.getMessage();
			throw new RuntimeException("11111");
		}
		
	}
}
