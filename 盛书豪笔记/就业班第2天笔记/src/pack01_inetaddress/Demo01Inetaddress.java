package pack01_inetaddress;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Demo01Inetaddress {

	public static void main(String[] args) throws UnknownHostException {
//		public static InetAddress getByName(String host) //����������������ַ��ȡIp
//		public String getHostName()     //��ȡ������
//		public String getHostAddress() //��ȡIP��ַ
		
		//ͨ����������ȡip
//		InetAddress id = InetAddress.getByName("LAPTOP-6F2V5QTF");
//		String ip = id.getHostAddress();
//		
//		System.out.println("ip:" + ip);
		
		//ͨ��ip��ȡ������
		//��ip�ַ���תΪInetAddress����
//		InetAddress id = InetAddress.getByName("192.168.160.82");
//		String hostName = id.getHostName();
//		System.out.println("hostName:" + hostName);
		
		//��ȡ�ٶȵĵ�ip
//		InetAddress id = InetAddress.getByName("www.baidu.com");
//		String ip = id.getHostAddress();
//		
//		System.out.println("�ٶ�ip:" + ip);
		
		
//		public static InetAddress getLocalHost() //��ȡ��������ip��������Ϣ
		
		InetAddress localHost = InetAddress.getLocalHost();
		System.out.println("ip:" + localHost.getHostAddress());
		System.out.println("������:" + localHost.getHostName());
		
		
	}

}
