package pack06_test;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerDemo {

	public static void main(String[] args) throws IOException {
		ServerSocket sSocket = new ServerSocket(12306);
		
		while(true){
			Socket cSocket = sSocket.accept();
			//��ÿһ���Ѿ������õ����ӣ�ͨ�������߳�������
			Thread t = new Thread(new MyRunnable(cSocket));
			t.start();
		}
		
	}

}
