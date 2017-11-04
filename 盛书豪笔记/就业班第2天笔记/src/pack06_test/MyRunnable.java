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
			//1����ȡ�׽��ֵ�������
			InputStream is = cSocket.getInputStream();
			//2����ȡ�ļ��������
			String fileName = "D:\\Server\\"+System.currentTimeMillis() + ".jpg";
			BufferedOutputStream bufos = new BufferedOutputStream(new FileOutputStream(fileName));
			
			//3�����׽��ֵ���������ȡ���ݣ�д���ļ��������
			byte[] bys = new byte[1024];
			int len;
			while((len = is.read(bys)) != -1){
				bufos.write(bys,0,len);
				bufos.flush();
			}
			
			//4:���ͻ��˷�����Ϣ
			OutputStream os = cSocket.getOutputStream();
			os.write("�ļ��ϴ��ɹ�".getBytes());
			
			cSocket.close();
			bufos.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
