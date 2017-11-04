package pack01_inetaddress;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.channels.DatagramChannel;
import java.util.Date;

public class Demo02UDPSend {

	public static void main(String[] args) throws IOException {
		//1:����DatagramSocket(�׽���)����:����һ����ͷ
		//���Ͷ�ʹ���޲ε�DatagramSocket����
		DatagramSocket ds = new DatagramSocket();
		
	    //2��׼��Ҫ���͵�����
		byte[] bys = "hello".getBytes();
		
		//3:����DatagramPacket����:׼��һ����װ��,�����ݷ��뼯װ���У���ָ��ip�Ͷ˿�
		/*
		 * ��1��ָ��Ҫ���͵�����
		 * ��2��ָ��Ҫ���͵����ݳ���
		 * ��3��ָ�����ն˵�ip
		 * ��4��ָ�����ն˵Ķ˿�
		 */
		//��ip�ַ���תΪInetAddress����
		InetAddress ip = InetAddress.getByName("192.168.160.82");
		DatagramPacket dp = new DatagramPacket(bys, bys.length,ip, 8888);
		
		//4:��������
		ds.send(dp);
		
		//5:�ر���Դ
		ds.close();
	}

}
