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
		//1:创建DatagramSocket(套接字)对象:有了一个码头
		//发送端使用无参的DatagramSocket构造
		DatagramSocket ds = new DatagramSocket();
		
	    //2：准备要发送的数据
		byte[] bys = "hello".getBytes();
		
		//3:创建DatagramPacket对象:准备一个集装箱,将数据放入集装箱中，并指定ip和端口
		/*
		 * 参1：指定要发送的数据
		 * 参2：指定要发送的数据长度
		 * 参3：指定接收端的ip
		 * 参4：指定接收端的端口
		 */
		//将ip字符串转为InetAddress对象
		InetAddress ip = InetAddress.getByName("192.168.160.82");
		DatagramPacket dp = new DatagramPacket(bys, bys.length,ip, 8888);
		
		//4:发送数据
		ds.send(dp);
		
		//5:关闭资源
		ds.close();
	}

}
