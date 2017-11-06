package com.itheima;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

//解析xml文件，修改xml文件，并重新写入xml文件。
public class Dom {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws DocumentException, IOException {
		SAXReader sr = new SAXReader();
		Document doc = sr.read("books.xml");
		Element re = doc.getRootElement();
		List<Element> elements = re.elements();
		for (Element e : elements) {
			Attribute att = e.attribute("id");
			if(att.getValue()=="hehe"){
				att.setValue("88888");
			}
		}
		//将修改后的内存中的xml存储到文件中
		FileOutputStream os = new FileOutputStream("books.xml");//创建输出流
		OutputFormat format = OutputFormat.createPrettyPrint();//获取输出的指定格式
		format.setEncoding("utf-8");
		XMLWriter writer = new XMLWriter(os,format);
		writer.write(doc);//将内存中的内容写入文件
		writer.flush();
		writer.close();
	}

}
