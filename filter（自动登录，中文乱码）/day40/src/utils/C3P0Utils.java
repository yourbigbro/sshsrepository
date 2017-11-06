package utils;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3P0Utils {

    private static DataSource dataSource = new ComboPooledDataSource();

    
    public static DataSource getDataSource() {
        return dataSource;
    }

}
