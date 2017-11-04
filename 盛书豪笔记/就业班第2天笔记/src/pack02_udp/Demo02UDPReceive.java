package pack02_udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Demo02UDPReceive {

	public static void main(String[] args) throws IOException {
		// 1:创建DatagramSocket对象，并指定端口号:码头
		// 端口号必须和发送端的一致
		DatagramSocket ds = new DatagramSocket(8888);

		// 2:创建DatagramPacket对象，指定接收数据的字节数组，接收的长度
		byte[] bys = new byte[1024]; // 1G
		DatagramPacket dp = new DatagramPacket(bys, bys.length); // 1 1024

		while (true) {
			// 3:接收数据
			ds.receive(dp); // 如果发送端没有发送数据，程序会阻塞在此

			// 从包里取出数据
			byte[] data = dp.getData();
			// 取出实际结束数据的长度
			int length = dp.getLength();

			// 获取发送端IP
			InetAddress address = dp.getAddress();
			String str = new String(data, 0, length);
			if (str.equals("exit")) {
				break;
			}
			// 打印数据
			System.out.println(address.getHostAddress() + ":" + str);
		}
		// 4:关闭资源
		ds.close();
	}

}
