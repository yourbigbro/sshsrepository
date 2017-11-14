package com.itheima;
//dbcp不带配置文件
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;

//通过DBUtils，将指定的汽车对象添加到数据库表car中。
//汽车对象: 奥迪厂商下的紧凑型SUV车（奥迪Q3)，产地德国，售价23.42W
public class Test1 {

	public static void main(String[] args) throws SQLException {
		Car car = new Car("奥迪Q3","奥迪", "德国", "紧凑型SUV", 23.42);//该对象的作用是获得参数。因此还应该从创建一个Car类。
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());//QueryRunner方法是DBUtils工具类里面的方法，里面提供了操作sql语句的api（方法）。传入连接池（而不是连接）对象做参数。
		String sql="insert into car (cname, company,address, grade, price) values (?,?,?,?,?)";
		Object[] ob=new Object[]{car.getCname(), car.getCompany(), car.getAddress(), car.getGrade(), car.getPrice()};
		int up = qr.update(sql,ob);//从连接池中自动取出一个连接。返回值up是数据库中被影响的行数。
		System.out.println("添加的汽车个数为："+up);
	}

}
