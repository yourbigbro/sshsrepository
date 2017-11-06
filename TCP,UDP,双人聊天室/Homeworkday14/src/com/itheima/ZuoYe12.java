package com.itheima;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/*编写UDP程序
要求:
发送端键盘输入内容,输入一行,发送到接收端
如果键盘输入的是 over 就结束发送
接收端接收输入,将接收到的内容转成大写输出到控制台
如果接收到over,就输出程序结束了*/
public class ZuoYe12 {

	public static void main(String[] args) throws IOException {
		//接收端
		DatagramSocket ds=new DatagramSocket(9999);//码头上只写端口号
		while(true){
			byte[] by=new byte[1024];
			int len=by.length;
			DatagramPacket dp = new DatagramPacket(by,len);//在包裹上写两个参数
			ds.receive(dp);
			int length = dp.getLength();//获得长度
			byte[] data = dp.getData();//获得字节数组
			System.out.println(new String(data,0,length).toUpperCase());
			if(new String(data,0,length).equals("over")){
				System.out.println("程序结束");
				break;
			}
		}
		
		ds.close();
	}

}
