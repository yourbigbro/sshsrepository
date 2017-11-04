package pack08_chat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientDemo {

	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket cSocket = new Socket("192.168.160.82",12306);
		

		//创建两个线程，一个线程读，一个线程写
		
		Thread tRead = new Thread(new ReadRunnable(cSocket));
		Thread tWrite = new Thread(new WriteRunnable(cSocket));
		
		tRead.start();
		tWrite.start();
	}

}
