package pack03_tcp;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Demo02Client {

	public static void main(String[] args) throws UnknownHostException, IOException {
		//1:����Socket����ָ����������Ip�Ͷ˿�
		//�÷������ڲ��Ѿ��������������֣��ͷ���������������ͨ��
		Socket socket = new Socket("192.168.160.82", 9999);
		
		//2����ȡ�����
		OutputStream os = socket.getOutputStream();
		
		//3:д����
		os.write("hello".getBytes());
		
		//4:�ر���Դ
		os.close();
		socket.close();
	}

}
