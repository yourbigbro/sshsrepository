package com.itheima;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/*实现TCP程序
	要求:
客户端键盘录入数据发送给服务器端,录入一行发一次
    服务器接收到客户端数据后,将数据转成大写回给客户端
    客户端读取服务器发回的大写数据
    如果客户端录入 over 就停止程序*/
public class ZuoYe21 {

	public static void main(String[] args) throws UnknownHostException, IOException {
		//客户端
		Socket socket = new Socket(InetAddress.getLocalHost().getHostAddress(),10086);//第一个参数是服务端的ip地址，是一个字符串。由于只是和本机通信，故写成了本机的ip地址。
		OutputStream os = socket.getOutputStream();
		InputStream is = socket.getInputStream();
		Scanner sc=new Scanner(System.in);
		while(true){//死循环，因此在该线程可以永远输入信息。
			System.out.println("请输入字母");
			String nl = sc.nextLine();
			os.write(nl.getBytes());//将键盘输入的信息发送给服务端
			byte[] by=new byte[1024];
			int len=is.read(by);//读取服务端返回的信息，并装在字节数组里
			System.out.println(new String(by,0,len));//读取并显示字节数组里的信息。
			if(nl.equals("over")){
				System.out.println("停止程序");
				break;
			}
		}
		socket.close();//关闭套接字，不用关闭输入输出流。
	}

}
