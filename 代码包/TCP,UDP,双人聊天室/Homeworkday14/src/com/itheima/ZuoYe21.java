package com.itheima;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/*ʵ��TCP����
	Ҫ��:
�ͻ��˼���¼�����ݷ��͸���������,¼��һ�з�һ��
    ���������յ��ͻ������ݺ�,������ת�ɴ�д�ظ��ͻ���
    �ͻ��˶�ȡ���������صĴ�д����
    ����ͻ���¼�� over ��ֹͣ����*/
public class ZuoYe21 {

	public static void main(String[] args) throws UnknownHostException, IOException {
		//�ͻ���
		Socket socket = new Socket(InetAddress.getLocalHost().getHostAddress(),10086);//��һ�������Ƿ���˵�ip��ַ����һ���ַ���������ֻ�Ǻͱ���ͨ�ţ���д���˱�����ip��ַ��
		OutputStream os = socket.getOutputStream();
		InputStream is = socket.getInputStream();
		Scanner sc=new Scanner(System.in);
		while(true){//��ѭ��������ڸ��߳̿�����Զ������Ϣ��
			System.out.println("��������ĸ");
			String nl = sc.nextLine();
			os.write(nl.getBytes());//�������������Ϣ���͸������
			byte[] by=new byte[1024];
			int len=is.read(by);//��ȡ����˷��ص���Ϣ����װ���ֽ�������
			System.out.println(new String(by,0,len));//��ȡ����ʾ�ֽ����������Ϣ��
			if(nl.equals("over")){
				System.out.println("ֹͣ����");
				break;
			}
		}
		socket.close();//�ر��׽��֣����ùر������������
	}

}
