package app;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;//ע�ⲻ�ǵ�tomcat�İ�

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
		filename=new String(filename.getBytes("iso-8859-1"),"utf-8");//�����̨��ʾ�������⡣Ϊʲô����gbk��������������
		
		ServletContext sercon = this.getServletContext();//����ServletContext����
		String fileType=sercon.getMimeType(filename);//����ServletContext����ķ�����֮�����ܹ��õ��ļ���������Ϊ������к�׺�����÷���ֻ�ǽ�ԭ��������������һ�¡�
		res.setHeader("Content-Type", fileType);//���õ�һ����Ӧͷ��ע����set
		
		String realPath = sercon.getRealPath("/download/"+filename);//ע��������������realPath�е�filename���ܲ�������ķ�����任
		FileInputStream fis = new FileInputStream(realPath);
		
		String header=req.getHeader("User-Agent");
		if(header.contains("Firefox")){//�������ҳ�����������
			filename=base64EncodeFileName(filename);
		}else {
			filename=URLEncoder.encode(filename,"utf-8");
		}
		//System.out.println(filename);�����%E5%9B%BE%E7%89%87.jpg
		res.addHeader("Content-Disposition", "attachment;filename="+filename);//���õڶ�����Ӧͷ��ע����add��ͷ�������Ҫ���б��룬�����ܴ������ģ����Ļᶪʧ
		
		
		ServletOutputStream os = res.getOutputStream();
		IOUtils.copy(fis, os);//�ù�����ķ������ڼ��������ĶԽӴ�����Ϣ
		os.close();
		fis.close();
	}
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException{
		doGet(req, res);
	}
	public static String base64EncodeFileName(String fileName) {//��������������봦��ʽ
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
