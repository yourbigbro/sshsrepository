package pack01_inetaddress;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Demo02UDPReceive {

	public static void main(String[] args) throws IOException {
		//1:����DatagramSocket���󣬲�ָ���˿ں�:��ͷ
		//�˿ںű���ͷ��Ͷ˵�һ��
		DatagramSocket ds = new DatagramSocket(8888);
		
		//2:����DatagramPacket����ָ���������ݵ��ֽ����飬���յĳ���
		byte[] bys = new byte[1024]; //1G 
		DatagramPacket dp = new DatagramPacket(bys, bys.length); //1  1024
		
		//3:��������
		ds.receive(dp); //������Ͷ�û�з������ݣ�����������ڴ�
		
		//�Ӱ���ȡ������
		byte[] data = dp.getData();
		//ȡ��ʵ�ʽ������ݵĳ���
		int length = dp.getLength();
		
		//��ȡ���Ͷ�IP
		InetAddress address = dp.getAddress();
		//��ӡ����
		System.out.println(address.getHostAddress()+":"+new String(data, 0, length));
		//		System.out.println("xxxxxxxxxxxxxxxxxxxx");
		//4:�ر���Դ
		ds.close();
	}

}
