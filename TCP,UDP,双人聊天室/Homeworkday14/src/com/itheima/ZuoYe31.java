package com.itheima;
/*TCP文件下载
要求:
客户端从服务器端下载图片
        服务器将d:\\upload1.jpg文件以流的形式写给客户端
        客户端从服务器读取文件,并保存到c:\download目录下
        保存成功后,控制台打印出下载完成*/
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class ZuoYe31 {

	public static void main(String[] args) throws IOException {
		// 客户端
		InetAddress localHost = InetAddress.getLocalHost();
		String hostAddress = localHost.getHostAddress();
		Socket socket = new Socket(hostAddress,10086);
		OutputStream os = socket.getOutputStream();
		
		InputStream is = socket.getInputStream();
		byte[] by=new byte[1024];
		int len1;
		String sstr="";
		os.write("请发送文件名".getBytes());//首先向服务器发送数据
		/*while((len1=is.read(by))!=-1){//接收名字
			sstr+=new String(by, 0, len1);
		}*/
		int nlen=is.read(by);
				
		sstr=new String(by, 0, nlen);
		System.out.println(sstr);
		BufferedOutputStream bufos=new BufferedOutputStream(new FileOutputStream("C:\\download\\"+sstr));
		os.write("名字已收到".getBytes());//向服务器反馈名字已经收到
		socket.shutdownOutput();
		//System.out.println("_----");
		byte[] bys=new byte[1024];
		int len;
		while((len=is.read(bys))!=-1){//接受内容
			bufos.write(bys, 0, len);
			bufos.flush();
		}
		//System.out.println("传输完成");
		bufos.close();
		socket.close();
		
	}

}
