package pack02_udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Demo02UDPSend {

	public static void main(String[] args) throws IOException {
		// 1:����DatagramSocket(�׽���)����:����һ����ͷ
		// ���Ͷ�ʹ���޲ε�DatagramSocket����
		DatagramSocket ds = new DatagramSocket();

		Scanner sc = new Scanner(System.in);
		while (true) {
			// 2��׼��Ҫ���͵�����

			// �Ӽ��̻�ȡ����
			System.out.println("������Ҫ���͵�����(����exit�˳�):");
			String str = sc.nextLine();
			byte[] bys = str.getBytes();
			// 3:����DatagramPacket����:׼��һ����װ��,�����ݷ��뼯װ���У���ָ��ip�Ͷ˿�
			// ��ip�ַ���תΪInetAddress����
			InetAddress ip = InetAddress.getByName("192.168.160.82");
			DatagramPacket dp = new DatagramPacket(bys, bys.length, ip, 8888);

			// 4:��������
			ds.send(dp);
			
			if(str.equals("exit")){
				break;
			}
			
		}

		// 5:�ر���Դ
		ds.close();
	}

}
