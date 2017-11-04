package pack02_udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Demo02UDPReceive {

	public static void main(String[] args) throws IOException {
		// 1:����DatagramSocket���󣬲�ָ���˿ں�:��ͷ
		// �˿ںű���ͷ��Ͷ˵�һ��
		DatagramSocket ds = new DatagramSocket(8888);

		// 2:����DatagramPacket����ָ���������ݵ��ֽ����飬���յĳ���
		byte[] bys = new byte[1024]; // 1G
		DatagramPacket dp = new DatagramPacket(bys, bys.length); // 1 1024

		while (true) {
			// 3:��������
			ds.receive(dp); // ������Ͷ�û�з������ݣ�����������ڴ�

			// �Ӱ���ȡ������
			byte[] data = dp.getData();
			// ȡ��ʵ�ʽ������ݵĳ���
			int length = dp.getLength();

			// ��ȡ���Ͷ�IP
			InetAddress address = dp.getAddress();
			String str = new String(data, 0, length);
			if (str.equals("exit")) {
				break;
			}
			// ��ӡ����
			System.out.println(address.getHostAddress() + ":" + str);
		}
		// 4:�ر���Դ
		ds.close();
	}

}
