package pack06_test;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientDemo {

	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket socket = new Socket("192.168.160.82",12306);
		
		//��ȡ�׽��ֵ������
		OutputStream os = socket.getOutputStream();
		//��ȡ�ļ���������
		BufferedInputStream bufis = new BufferedInputStream(new FileInputStream("����.jpg"));
		
		//���ļ�����������ȡ����,д���׽��ֵ������
		byte[] bys = new byte[1024];
		int len ;
		while((len = bufis.read(bys)) != -1){
			os.write(bys, 0, len);
		}
		
		//----�ͻ����ڴ˹ر�����������߷��������ͻ���û������Ҫ������
		socket.shutdownOutput();
		
		//-----��ȡ������Ϣ---------------
		InputStream is = socket.getInputStream();
		len = is.read(bys);
		System.out.println(new String(bys, 0, len));
		
		//�ر���Դ
		socket.close();
		bufis.close();
	}

}
