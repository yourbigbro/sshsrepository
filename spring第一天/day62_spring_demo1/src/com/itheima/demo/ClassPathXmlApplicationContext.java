package com.itheima.demo;

import org.dom4j.Document;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
//这是Spring的工具类
public class ClassPathXmlApplicationContext {

	private String xmlPath;

	public ClassPathXmlApplicationContext(String xmlPath) {
		this.xmlPath = xmlPath;
	}

	//根据id得到指定的对象
	public Object getBean(String id) throws Exception {
		SAXReader reader = new SAXReader();
		//bean.xml配置文件放在src目录下，所以路径里面有src
	    Document document = reader.read("src/"+xmlPath);
	    //根据id属性得到指定的标签
	    Node node = document.selectSingleNode("//bean[@id='"+id+"']");
	    //得到class属性的值
	    String className = node.valueOf("@class");
	    //通过反射得到对象
	    Object obj = Class.forName(className).newInstance();
		return obj;
	}

	
}
