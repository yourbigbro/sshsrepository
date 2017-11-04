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
		//获取输出流
		try {
		 OutputStream os = cSocket.getOutputStream();//老师可能应该关闭流
		 Scanner sc = new Scanner(System.in);
		 while(true){
			 System.out.println("请输入数据:");
			 String str = sc.nextLine();
			 
			 os.write(str.getBytes());
		 }
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
