package pack06_test;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerDemo {

	public static void main(String[] args) throws IOException {
		ServerSocket sSocket = new ServerSocket(12306);
		
		while(true){
			Socket cSocket = sSocket.accept();
			//对每一个已经建立好的连接，通过创建线程来处理
			Thread t = new Thread(new MyRunnable(cSocket));
			t.start();
		}
		
	}

}
