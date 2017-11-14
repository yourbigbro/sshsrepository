package com.itheima2;
//dbcp带配置文件
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;

//通过DBUtils，将指定的汽车对象添加到数据库表car中。
//汽车对象: 奥迪厂商下的紧凑型SUV车（奥迪Q3)，产地德国，售价23.42W
public class Test1 {
    //与dbcp不带配置文件的Test1测试类完全相同。
	public static void main(String[] args) throws SQLException {
		Car car = new Car("夏利Q3","夏利", "中国", "三轮", 5.0);
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql="insert into car (cname, company,address, grade, price) values (?,?,?,?,?)";
		Object[] ob=new Object[]{car.getCname(), car.getCompany(), car.getAddress(), car.getGrade(), car.getPrice()};
		int up = qr.update(sql,ob);
		System.out.println("添加的汽车个数为："+up);
	}

}

