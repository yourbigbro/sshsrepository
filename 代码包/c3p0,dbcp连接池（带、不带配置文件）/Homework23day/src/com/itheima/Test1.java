package com.itheima;
//dbcp���������ļ�
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;

//ͨ��DBUtils����ָ��������������ӵ����ݿ��car�С�
//��������: �µϳ����µĽ�����SUV�����µ�Q3)�����ص¹����ۼ�23.42W
public class Test1 {

	public static void main(String[] args) throws SQLException {
		Car car = new Car("�µ�Q3","�µ�", "�¹�", "������SUV", 23.42);//�ö���������ǻ�ò�������˻�Ӧ�ôӴ���һ��Car�ࡣ
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());//QueryRunner������DBUtils����������ķ����������ṩ�˲���sql����api�����������������ӳأ����������ӣ�������������
		String sql="insert into car (cname, company,address, grade, price) values (?,?,?,?,?)";
		Object[] ob=new Object[]{car.getCname(), car.getCompany(), car.getAddress(), car.getGrade(), car.getPrice()};
		int up = qr.update(sql,ob);//�����ӳ����Զ�ȡ��һ�����ӡ�����ֵup�����ݿ��б�Ӱ���������
		System.out.println("��ӵ���������Ϊ��"+up);
	}

}
