package pack06_test;

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
			//2：获取文件的输出流
			String fileName = "D:\\Server\\"+System.currentTimeMillis() + ".jpg";
			BufferedOutputStream bufos = new BufferedOutputStream(new FileOutputStream(fileName));
			
			//3：从套接字的输入流读取数据，写入文件的输出流
			byte[] bys = new byte[1024];
			int len;
			while((len = is.read(bys)) != -1){
				bufos.write(bys,0,len);
				bufos.flush();
			}
			
			//4:给客户端反馈信息
			OutputStream os = cSocket.getOutputStream();
			os.write("文件上传成功".getBytes());
			
			cSocket.close();
			bufos.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
