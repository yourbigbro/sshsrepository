package pack04_tcp_file;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerDemo {

	public static void main(String[] args) throws IOException {
		ServerSocket sSocket = new ServerSocket(10086);
		
		Socket cSocket = sSocket.accept();
		
		//��ȡ�׽��ֵ�������
		InputStream is = cSocket.getInputStream();
		//��ȡ�ļ��������
		BufferedOutputStream bufos = 
				new BufferedOutputStream(new FileOutputStream("liutao.jpg"));
		
		//���׽��ֵ���������ȡ���ݣ�д���ļ��������
		byte[] bys  = new byte[1024];
		int len ;
		while((len = is.read(bys)) != -1){
			bufos.write(bys, 0, len);
			//ˢ��
			bufos.flush();
		}
		//�ر���Դ
		sSocket.close();
		cSocket.close();
		is.close();
		bufos.close();
		
	}

}
