package com.itheima;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

//˫��������
public class ZuoYe41 {

	public static void main(String[] args) throws UnknownHostException, IOException {
		// �ͻ���
		Socket socket = new Socket(InetAddress.getLocalHost().getHostAddress(),10000);
		Thread thread=new Thread(new ReadRunnable(socket));
		Thread thwrite=new Thread(new WriteRunnable(socket));
		thread.start();
		thwrite.start();
	}

}
