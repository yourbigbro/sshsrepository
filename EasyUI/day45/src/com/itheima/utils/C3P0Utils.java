package com.itheima.utils;

import java.sql.Connection;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * �ṩһ��������Ӷ���ķ���
 * 
 * @author Never Say Never
 * @date 2017��5��8��
 * @version V1.0
 */
public class C3P0Utils {

    /**
     * ����c3p0-config.xml�ļ���Ĭ��������
     */
    private static DataSource dataSource = new ComboPooledDataSource();

    // ����һ��map���Connection����
    private static ThreadLocal<Connection> local = new ThreadLocal<>();

    /**
     * �ṩ������Ӷ���ķ���(��ThreadLocal�л��Connection)
     * 
     * @return
     */
    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = local.get();
            // �ж�
            if (conn == null) {
                local.set(dataSource.getConnection());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return local.get();
    }

    /**
     * �ṩһ���������Դ�ķ���
     */
    public static DataSource getDataSource() {
        return dataSource;
    }

}
