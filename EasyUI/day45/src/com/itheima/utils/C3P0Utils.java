package com.itheima.utils;

import java.sql.Connection;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 提供一个获得连接对象的方法
 * 
 * @author Never Say Never
 * @date 2017年5月8日
 * @version V1.0
 */
public class C3P0Utils {

    /**
     * 加载c3p0-config.xml文件的默认配置项
     */
    private static DataSource dataSource = new ComboPooledDataSource();

    // 定义一个map存放Connection对象！
    private static ThreadLocal<Connection> local = new ThreadLocal<>();

    /**
     * 提供获得连接对象的方法(从ThreadLocal中获得Connection)
     * 
     * @return
     */
    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = local.get();
            // 判断
            if (conn == null) {
                local.set(dataSource.getConnection());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return local.get();
    }

    /**
     * 提供一个获得数据源的方法
     */
    public static DataSource getDataSource() {
        return dataSource;
    }

}
