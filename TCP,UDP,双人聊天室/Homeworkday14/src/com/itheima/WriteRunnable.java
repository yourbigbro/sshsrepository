package com.itheima;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class WriteRunnable implements Runnable  {
	Socket socket;
	public WriteRunnable() {//空参构造函数
		
	}

	public WriteRunnable(Socket socket) {//满参构造函数
		this.socket=socket;
	}
	@Override
	public void run() {//覆写run方法
		// TODO Auto-generated method stub
		OutputStream os = null;//将输出流设置为全局变量
		try {
			os = socket.getOutputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Scanner sc=new Scanner(System.in);
		
		try {while(true){//死循环，一直可以写
			String nl = sc.nextLine();//nextLine()是阻塞方法，输入数字之前会一直阻塞。
			os.write(nl.getBytes());//写一行就发送一行。write就代表发送。
		}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			socket.close();//关闭套接字
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			os.close();//关闭流。应该是不用关闭，套接字都没了，流也就跟着没有了。
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
