package com.itheima;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
/*TCP文件下载
要求:
客户端从服务器端下载图片
        服务器将d: upload1 .jpg文件以流的形式写给客户端
        客户端从服务器读取文件,并保存到c:\download目录下
        保存成功后,控制台打印出下载完成*/
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ZuoYe32 {

	public static void main(String[] args) throws IOException {
		// 服务端
		ServerSocket ss = new ServerSocket(10086);
		Socket accept = ss.accept();
		//接收先接收客户端数据避免阻塞
		InputStream is = accept.getInputStream();
		int read = is.read(new byte[1024]);//
		OutputStream os = accept.getOutputStream();
		
		File fi=new File("D:\\upload1.jpg");
		BufferedInputStream bufis=new BufferedInputStream(new FileInputStream(fi));
		String name = fi.getName();
		os.write(name.getBytes());//传输名字
		is.read(new byte[1024]);//接受客户端发来的反馈
		System.out.println("1111222");
		int len;
		byte[] by=new byte[1024];
		while((len=bufis.read(by))!=-1){//传输内容
			os.write(by,0,len);
		}
		accept.shutdownOutput();///////////////
		bufis.close();
		ss.close();
	}

}
