package pack01_inetaddress;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Demo01Inetaddress {

	public static void main(String[] args) throws UnknownHostException {
//		public static InetAddress getByName(String host) //根据主机名或者网址获取Ip
//		public String getHostName()     //获取主机名
//		public String getHostAddress() //获取IP地址
		
		//通过主机名获取ip
//		InetAddress id = InetAddress.getByName("LAPTOP-6F2V5QTF");
//		String ip = id.getHostAddress();
//		
//		System.out.println("ip:" + ip);
		
		//通过ip获取主机名
		//将ip字符串转为InetAddress对象
//		InetAddress id = InetAddress.getByName("192.168.160.82");
//		String hostName = id.getHostName();
//		System.out.println("hostName:" + hostName);
		
		//获取百度的的ip
//		InetAddress id = InetAddress.getByName("www.baidu.com");
//		String ip = id.getHostAddress();
//		
//		System.out.println("百度ip:" + ip);
		
		
//		public static InetAddress getLocalHost() //获取本主机的ip和其他信息
		
		InetAddress localHost = InetAddress.getLocalHost();
		System.out.println("ip:" + localHost.getHostAddress());
		System.out.println("主机名:" + localHost.getHostName());
		
		
	}

}
