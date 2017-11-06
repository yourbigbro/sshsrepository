package com.itheima;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/*实现TCP程序
	要求:
客户端键盘录入数据发送给服务器端,录入一行发一次
    服务器接收到客户端数据后,将数据转成大写回给客户端
    客户端读取服务器发回的大写数据
    如果客户端录入 over 就停止程序*/
public class ZuoYe22 {

	public static void main(String[] args) throws IOException {
		// 服务端
		ServerSocket ss = new ServerSocket(10086);//只写端口号不写ip地址字符串
		Socket accept = ss.accept();
		InputStream is = accept.getInputStream();
		OutputStream os = accept.getOutputStream();
		while(true){
			byte[] by=new byte[1024];
			int len;
			len=is.read(by);
			String str=new String(by, 0, len);
			String str1=str.toUpperCase();
			os.write(str1.getBytes());
			if(str.equals("over")){
				System.out.println("程序结束");
				break;
			}
		}
		accept.close();//关闭客户端套接字
		ss.close();//关闭服务端套接字
	}

}
