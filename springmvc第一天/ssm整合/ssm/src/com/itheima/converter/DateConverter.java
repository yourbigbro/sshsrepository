package com.itheima.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

public class DateConverter implements Converter<String , Date>{

	@Override
	public Date convert(String string) {
		
		try {
			Date date = new SimpleDateFormat("yy-MM-dd HH:mm:ss").parse(string);
			return date;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//假如没有能够转换成Date数据类型的话就返回null
		return null;
	}

	
}
