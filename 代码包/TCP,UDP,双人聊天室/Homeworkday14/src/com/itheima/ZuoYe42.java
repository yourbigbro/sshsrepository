package com.itheima;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

//˫��������
public class ZuoYe42 {

	public static void main(String[] args) throws IOException {
		//��������
		ServerSocket ss=new ServerSocket(10000);
		Socket socket = ss.accept();
		Thread thread=new Thread(new ReadRunnable(socket));
		Thread thwrite=new Thread(new WriteRunnable(socket));
		thread.start();
		thwrite.start();
	}

	

	

}
