package com.itheima;

import java.util.Scanner;

public class App {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("��������������");
		String s1 = sc.next();
		System.out.println("�������տ�������");
		String s2 = sc.next();
		System.out.println("������ת�˽��");
		Double s3 = sc.nextDouble();
		//���ӷ���㣬���÷����ķ�����
		String str=Service.caoZuo(s1,s2,s3);
		System.out.println(str);
	}

}
