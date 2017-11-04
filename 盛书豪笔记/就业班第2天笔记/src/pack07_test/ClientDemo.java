package pack07_test;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientDemo {

	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket socket = new Socket("192.168.160.82",12306);
		
		//获取套接字的输出流
		OutputStream os = socket.getOutputStream();
		InputStream is = socket.getInputStream();
		//获取文件的输入流
		File file = new File("修饰符1.java");
		BufferedInputStream bufis = new BufferedInputStream(new FileInputStream(file));
		
		//给服务器发送文件的名字
		os.write(file.getName().getBytes());
		
		//读取服务器的反馈文件名的信息
		is.read(new byte[1024]); //读取的唯一目的是让程序在这里阻塞
		
		
		//从文件的输入流读取数据,写入套接字的输出流
		byte[] bys = new byte[1024];
		int len ;
		while((len = bufis.read(bys)) != -1){
			os.write(bys, 0, len);
		}
		
		//----客户端在此关闭输出流，告诉服务器，客户端没有数据要发送了
		socket.shutdownOutput();
		
		//-----读取反馈信息---------------
	
		len = is.read(bys);
		System.out.println(new String(bys, 0, len));
		
		//关闭资源
		socket.close();
		bufis.close();
	}

}
