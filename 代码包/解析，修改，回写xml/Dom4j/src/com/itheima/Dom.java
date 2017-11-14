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

//����xml�ļ����޸�xml�ļ���������д��xml�ļ���
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
		//���޸ĺ���ڴ��е�xml�洢���ļ���
		FileOutputStream os = new FileOutputStream("books.xml");//���������
		OutputFormat format = OutputFormat.createPrettyPrint();//��ȡ�����ָ����ʽ
		format.setEncoding("utf-8");
		XMLWriter writer = new XMLWriter(os,format);
		writer.write(doc);//���ڴ��е�����д���ļ�
		writer.flush();
		writer.close();
	}

}
