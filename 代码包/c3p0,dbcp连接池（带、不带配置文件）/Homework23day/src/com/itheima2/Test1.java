package com.itheima2;
//dbcp�������ļ�
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;

//ͨ��DBUtils����ָ��������������ӵ����ݿ��car�С�
//��������: �µϳ����µĽ�����SUV�����µ�Q3)�����ص¹����ۼ�23.42W
public class Test1 {
    //��dbcp���������ļ���Test1��������ȫ��ͬ��
	public static void main(String[] args) throws SQLException {
		Car car = new Car("����Q3","����", "�й�", "����", 5.0);
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql="insert into car (cname, company,address, grade, price) values (?,?,?,?,?)";
		Object[] ob=new Object[]{car.getCname(), car.getCompany(), car.getAddress(), car.getGrade(), car.getPrice()};
		int up = qr.update(sql,ob);
		System.out.println("��ӵ���������Ϊ��"+up);
	}

}

