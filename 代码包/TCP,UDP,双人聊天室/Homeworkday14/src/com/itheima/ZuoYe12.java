package com.itheima;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/*��дUDP����
Ҫ��:
���Ͷ˼�����������,����һ��,���͵����ն�
�������������� over �ͽ�������
���ն˽�������,�����յ�������ת�ɴ�д���������̨
������յ�over,��������������*/
public class ZuoYe12 {

	public static void main(String[] args) throws IOException {
		//���ն�
		DatagramSocket ds=new DatagramSocket(9999);//��ͷ��ֻд�˿ں�
		while(true){
			byte[] by=new byte[1024];
			int len=by.length;
			DatagramPacket dp = new DatagramPacket(by,len);//�ڰ�����д��������
			ds.receive(dp);
			int length = dp.getLength();//��ó���
			byte[] data = dp.getData();//����ֽ�����
			System.out.println(new String(data,0,length).toUpperCase());
			if(new String(data,0,length).equals("over")){
				System.out.println("�������");
				break;
			}
		}
		
		ds.close();
	}

}
