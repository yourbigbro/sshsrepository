package app;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;//注意不是导tomcat的包

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;

import sun.misc.BASE64Encoder;

public class DownloadServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException{
		
		String filename = req.getParameter("filename");
		filename=new String(filename.getBytes("iso-8859-1"),"utf-8");//解决后台显示乱码问题。为什么不是gbk？？？？？？？
		
		ServletContext sercon = this.getServletContext();//创建ServletContext对象
		String fileType=sercon.getMimeType(filename);//调用ServletContext对象的方法，之所以能够得到文件类型是因为本身就有后缀名，该方法只是将原来的名字完整了一下。
		res.setHeader("Content-Type", fileType);//设置第一个响应头。注意是set
		
		String realPath = sercon.getRealPath("/download/"+filename);//注意用于输入流的realPath中的filename不能参与下面的防乱码变换
		FileInputStream fis = new FileInputStream(realPath);
		
		String header=req.getHeader("User-Agent");
		if(header.contains("Firefox")){//解决下载页面的中文问题
			filename=base64EncodeFileName(filename);
		}else {
			filename=URLEncoder.encode(filename,"utf-8");
		}
		//System.out.println(filename);结果是%E5%9B%BE%E7%89%87.jpg
		res.addHeader("Content-Disposition", "attachment;filename="+filename);//设置第二个响应头。注意是add。头里的内容要进行编码，否则不能传输中文，中文会丢失
		
		
		ServletOutputStream os = res.getOutputStream();
		IOUtils.copy(fis, os);//该工具类的方法用于简化两个流的对接传输信息
		os.close();
		fis.close();
	}
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException{
		doGet(req, res);
	}
	public static String base64EncodeFileName(String fileName) {//定义火狐浏览器编码处理方式
		BASE64Encoder base64Encoder = new BASE64Encoder();
		try {
			return "=?UTF-8?B?"
					+ new String(base64Encoder.encode(fileName
							.getBytes("UTF-8"))) + "?=";
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
