package com.itheima;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

public class Test {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		// 将文件中的内容读取到集合
		Properties pro = new Properties();//创建properties对象
		pro.load(new FileReader("a.txt"));//字符流做参数
		Set<String> set = pro.stringPropertyNames();//获取键的集合
		for (String key : set) {
			pro.getProperty(key);//由键获得值。
		}
	}

}
