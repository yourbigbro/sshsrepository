package com.itheima;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

/*编写UDP程序
	要求:
发送端键盘输入内容,输入一行,发送到接收端
    如果键盘输入的是 over 就结束发送
    接收端接收输入,将接收到的内容转成大写输出到控制台
    如果接收到over,就输出程序结束了*/
public class ZuoYe11 {

	public static void main(String[] args) throws IOException {
		//发送端
		DatagramSocket ds = new DatagramSocket();//没有参数。相当于码头
		Scanner sc=new Scanner(System.in);
		while(true){
			String nl = sc.nextLine();
			byte[] by=nl.getBytes();
			InetAddress localHost = InetAddress.getLocalHost();//获得本机inetAddress对象。getLocalHost应该是类的静态方法。
			DatagramPacket dp = new DatagramPacket(by,by.length,localHost,9999);//相当于包裹。在包裹上面写信息而不是在码头上面
			ds.send(dp);
			if(nl.equals("over")){
				break;
			}
		}
		ds.close();
	}

}
