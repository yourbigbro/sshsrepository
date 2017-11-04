package pack04_tcp_file;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientDemo {

	public static void main(String[] args) throws Exception, IOException {
		Socket socket = new Socket("192.168.160.82",10086);
		
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
		
		//�ر���Դ
		socket.close();
		os.close();
		bufis.close();
	}

}
