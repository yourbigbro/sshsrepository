package pack08_chat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerDemo {

	public static void main(String[] args) throws IOException {
		ServerSocket sSocket = new ServerSocket(12306);
		
		//�ͻ���ֻ��һ��������acceptֻ��Ҫ����һ��
		Socket cSocket = sSocket.accept();
		
		//���������̣߳�һ���̶߳���һ���߳�д
		
		Thread tRead = new Thread(new ReadRunnable(cSocket));
		Thread tWrite = new Thread(new WriteRunnable(cSocket));
		
		tRead.start();
		tWrite.start();
		
	}

}
