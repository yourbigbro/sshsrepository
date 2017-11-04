package pack02_udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Demo02UDPSend {

	public static void main(String[] args) throws IOException {
		// 1:创建DatagramSocket(套接字)对象:有了一个码头
		// 发送端使用无参的DatagramSocket构造
		DatagramSocket ds = new DatagramSocket();

		Scanner sc = new Scanner(System.in);
		while (true) {
			// 2：准备要发送的数据

			// 从键盘获取数据
			System.out.println("请输入要发送的数据(输入exit退出):");
			String str = sc.nextLine();
			byte[] bys = str.getBytes();
			// 3:创建DatagramPacket对象:准备一个集装箱,将数据放入集装箱中，并指定ip和端口
			// 将ip字符串转为InetAddress对象
			InetAddress ip = InetAddress.getByName("192.168.160.82");
			DatagramPacket dp = new DatagramPacket(bys, bys.length, ip, 8888);

			// 4:发送数据
			ds.send(dp);
			
			if(str.equals("exit")){
				break;
			}
			
		}

		// 5:关闭资源
		ds.close();
	}

}
