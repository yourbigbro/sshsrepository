package com.itheima;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

/*��дUDP����
	Ҫ��:
���Ͷ˼�����������,����һ��,���͵����ն�
    �������������� over �ͽ�������
    ���ն˽�������,�����յ�������ת�ɴ�д���������̨
    ������յ�over,��������������*/
public class ZuoYe11 {

	public static void main(String[] args) throws IOException {
		//���Ͷ�
		DatagramSocket ds = new DatagramSocket();//û�в������൱����ͷ
		Scanner sc=new Scanner(System.in);
		while(true){
			String nl = sc.nextLine();
			byte[] by=nl.getBytes();
			InetAddress localHost = InetAddress.getLocalHost();//��ñ���inetAddress����getLocalHostӦ������ľ�̬������
			DatagramPacket dp = new DatagramPacket(by,by.length,localHost,9999);//�൱�ڰ������ڰ�������д��Ϣ����������ͷ����
			ds.send(dp);
			if(nl.equals("over")){
				break;
			}
		}
		ds.close();
	}

}
