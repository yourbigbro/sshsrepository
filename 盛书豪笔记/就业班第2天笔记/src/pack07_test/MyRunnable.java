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
			//1����ȡ�׽��ֵ�������
			InputStream is = cSocket.getInputStream();
			OutputStream os = cSocket.getOutputStream();
			
			byte[] bys = new byte[1024];
			int len;
			//��ȡ�ͻ��˷��͹������ļ���
			len = is.read(bys);
			String name = new String(bys, 0 , len);
			
			//���Է�һ������
			os.write("�յ��ļ���".getBytes());
			
			
			//2����ȡ�ļ��������
			String fileName = "D:\\Server\\"+name;
			BufferedOutputStream bufos = new BufferedOutputStream(new FileOutputStream(fileName));
			
			//3�����׽��ֵ���������ȡ���ݣ�д���ļ��������
	
			while((len = is.read(bys)) != -1){
				bufos.write(bys,0,len);
				bufos.flush();
			}
			
			//4:���ͻ��˷�����Ϣ
			os.write("�ļ��ϴ��ɹ�".getBytes());
			
			cSocket.close();
			bufos.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
