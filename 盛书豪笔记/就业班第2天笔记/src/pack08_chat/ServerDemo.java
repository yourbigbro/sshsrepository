package pack08_chat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerDemo {

	public static void main(String[] args) throws IOException {
		ServerSocket sSocket = new ServerSocket(12306);
		
		//客户端只有一个，所以accept只需要调用一次
		Socket cSocket = sSocket.accept();
		
		//创建两个线程，一个线程读，一个线程写
		
		Thread tRead = new Thread(new ReadRunnable(cSocket));
		Thread tWrite = new Thread(new WriteRunnable(cSocket));
		
		tRead.start();
		tWrite.start();
		
	}

}
