package com.itheima;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

//双人聊天室
public class ZuoYe42 {

	public static void main(String[] args) throws IOException {
		//服务器端
		ServerSocket ss=new ServerSocket(10000);
		Socket socket = ss.accept();
		Thread thread=new Thread(new ReadRunnable(socket));
		Thread thwrite=new Thread(new WriteRunnable(socket));
		thread.start();
		thwrite.start();
	}

	

	

}
