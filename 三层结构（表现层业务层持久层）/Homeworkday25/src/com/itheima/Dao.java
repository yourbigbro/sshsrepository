package com.itheima;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

public class Dao {
	//queryName方法里面用连接是因为query方法里面用连接。
	public  Account queryName(String name) throws SQLException{
		//QueryRunner在服务层的每个方法里面都有一个。
		QueryRunner qr = new QueryRunner();//QueryRunner处于持久层，，因为用于连接数据库。当用事务的时候不用传入连接池对象，只需在下面的query方法里面传入连接池中的具体连接，因为连接是固定的，而不能是任意连接。
		Connection con=ThreadLocalUtils.getConn();//该连接与开启事务的连接是一个连接，他的目的不是再次定义一个连接，而是引用一个保存在ThreadLocal中的连接池中的连接。
		System.out.println(con+"就");
		String sql="select * from zhuanzhang where aname=?";//变量一定要放在query方法里面做参数，否则放在sql字符串里面的话就会被当成字符串而不是变量。
		Account que = qr.query(con, sql, new BeanHandler<Account>(Account.class),name);//传入连接。
		//ThreadLocalUtils.commit();不用提交事务，应该放在service层（即业务层）里面使用。
	
		return que;
	}
	//updateMoney方法里面用连接是因为query方法里面用连接。
    public  void updateMoney(String name,Double money) throws SQLException{
    	QueryRunner qr = new QueryRunner();//当用事务的时候不用传入连接池对象，只需在下面的query方法里面传入连接池中的具体连接，因为连接是固定的，而不能是任意连接。
		Connection con=ThreadLocalUtils.getConn();//该连接与开启事务的连接是一个连接，他的目的不是再次定义一个连接，而是引用一个保存在ThreadLocal中的连接池中的连接。
		String sql="update zhuanzhang set amoney=? where aname=?";
	    qr.update(con, sql,money,name);//变量一定要放在query方法里面做参数，否则放在sql字符串里面的话就会被当成字符串而不是变量。传入连接。
		//ThreadLocalUtils.commit();不用提交事务，应该放在service层（即业务层）里面使用。
	    }
}
