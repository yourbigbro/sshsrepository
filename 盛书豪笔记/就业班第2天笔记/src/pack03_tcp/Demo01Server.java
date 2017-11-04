package pack03_tcp;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Demo01Server {

	public static void main(String[] args) throws IOException {
		//1:����ServerSocket����,��ָ���˿ں�
		ServerSocket sSocket = new ServerSocket(9999);
		
		System.out.println("���ڵȴ��ͻ�������.......");
		//2:����ͻ�������������ܿͻ��˵�����
		//û�����󣬸÷�������
		//���ص����socket��ʾ�Ѿ������õ�����,���socket�������˿ͻ��˵���Ϣ
		Socket cSocket = sSocket.accept();
		
		
		//�������ִ�е����˵���пͻ����Ѿ����������󣬶����Ѿ���������
		 //3:������Ҫ��ȡ���ݣ����ȴӽ����õ�ͨ���л�ȡ������
		InputStream is = cSocket.getInputStream();
		
		//4:��ȡ����
		byte[] bys = new byte[1024];
		int len = is.read(bys);
		
		//��ȡ�ͻ��˵�ip
		InetAddress inetAddress = cSocket.getInetAddress();
		String ip = inetAddress.getHostAddress();
		//��ȡ�˿�
		int port = cSocket.getPort();
		//5:��ӡ����
		
		System.out.println(ip+":"+new String(bys, 0, len));
		
		
		//6:�ر���Դ
		sSocket.close();
		cSocket.close();
		is.close();
	}

}
