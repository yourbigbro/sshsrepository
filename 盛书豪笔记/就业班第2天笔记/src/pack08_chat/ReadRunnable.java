package pack08_chat;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class ReadRunnable implements Runnable {

	Socket cSocket;

	public ReadRunnable() {
	}

	public ReadRunnable(Socket cSocket) {
		this.cSocket = cSocket;
	}

	@Override
	public void run() {
		try {
			InputStream is = cSocket.getInputStream();//��ʦ����Ӧ�ùر�����
			byte[] bys = new byte[1024];
			int len;
			while (true) {
				// ��ȡ����
				len = is.read(bys);
				System.out.println("��ȡ����:" + new String(bys, 0, len));//���������ֽ�����ת�����ַ���
			}
		} catch (IOException e) {
		}

	}

}
