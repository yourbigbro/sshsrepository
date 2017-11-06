package com.itheima;

import java.util.Scanner;

public class App {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("请输入汇款人名字");
		String s1 = sc.next();
		System.out.println("请输入收款人名字");
		String s2 = sc.next();
		System.out.println("请输入转账金额");
		Double s3 = sc.nextDouble();
		//连接服务层，调用服务层的方法。
		String str=Service.caoZuo(s1,s2,s3);
		System.out.println(str);
	}

}
