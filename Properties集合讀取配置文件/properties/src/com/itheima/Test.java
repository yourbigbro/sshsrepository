package com.itheima;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

public class Test {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		// ���ļ��е����ݶ�ȡ������
		Properties pro = new Properties();//����properties����
		pro.load(new FileReader("a.txt"));//�ַ���������
		Set<String> set = pro.stringPropertyNames();//��ȡ���ļ���
		for (String key : set) {
			pro.getProperty(key);//�ɼ����ֵ��
		}
	}

}
