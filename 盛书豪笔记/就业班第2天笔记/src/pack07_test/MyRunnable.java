package pack07_test;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class MyRunnable implements Runnable {

	Socket cSocket;
	
	public MyRunnable() {
	}
	
	public MyRunnable(Socket cSocket) {
		this.cSocket = cSocket;
	}

	@Override
	public void run() {
		try {
			//1：获取套接字的输入流
			InputStream is = cSocket.getInputStream();
			OutputStream os = cSocket.getOutputStream();
			
			byte[] bys = new byte[1024];
			int len;
			//读取客户端发送过来的文件名
			len = is.read(bys);
			String name = new String(bys, 0 , len);
			
			//给对方一个反馈
			os.write("收到文件名".getBytes());
			
			
			//2：获取文件的输出流
			String fileName = "D:\\Server\\"+name;
			BufferedOutputStream bufos = new BufferedOutputStream(new FileOutputStream(fileName));
			
			//3：从套接字的输入流读取数据，写入文件的输出流
	
			while((len = is.read(bys)) != -1){
				bufos.write(bys,0,len);
				bufos.flush();
			}
			
			//4:给客户端反馈信息
			os.write("文件上传成功".getBytes());
			
			cSocket.close();
			bufos.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
