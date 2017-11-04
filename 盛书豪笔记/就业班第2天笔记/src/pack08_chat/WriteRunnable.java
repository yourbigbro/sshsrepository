package pack08_chat;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class WriteRunnable implements Runnable {

	Socket cSocket;
	
	public WriteRunnable() {
	}
	
	public WriteRunnable(Socket cSocket) {
		this.cSocket = cSocket;
	}

	@Override
	public void run() {
		//��ȡ�����
		try {
		 OutputStream os = cSocket.getOutputStream();//��ʦ����Ӧ�ùر���
		 Scanner sc = new Scanner(System.in);
		 while(true){
			 System.out.println("����������:");
			 String str = sc.nextLine();
			 
			 os.write(str.getBytes());
		 }
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
