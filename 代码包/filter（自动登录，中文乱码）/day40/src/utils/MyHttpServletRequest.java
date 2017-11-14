package utils;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class MyHttpServletRequest extends HttpServletRequestWrapper{
	private HttpServletRequest request;
	
	public MyHttpServletRequest(HttpServletRequest request) {
		super(request);
		this.request=request;
	}
	
	public String getParameter(String str){
		String pa = request.getParameter(str);
		try {
			pa=new String(pa.getBytes("iso-8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			System.out.println("±àÂë×ª»»Òì³£");
			e.printStackTrace();
		}
		return pa;
	}
}
