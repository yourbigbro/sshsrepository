package com.itheima;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class WriteRunnable implements Runnable  {
	Socket socket;
	public WriteRunnable() {//�ղι��캯��
		
	}

	public WriteRunnable(Socket socket) {//���ι��캯��
		this.socket=socket;
	}
	@Override
	public void run() {//��дrun����
		// TODO Auto-generated method stub
		OutputStream os = null;//�����������Ϊȫ�ֱ���
		try {
			os = socket.getOutputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Scanner sc=new Scanner(System.in);
		
		try {while(true){//��ѭ����һֱ����д
			String nl = sc.nextLine();//nextLine()��������������������֮ǰ��һֱ������
			os.write(nl.getBytes());//дһ�оͷ���һ�С�write�ʹ����͡�
		}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			socket.close();//�ر��׽���
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			os.close();//�ر�����Ӧ���ǲ��ùرգ��׽��ֶ�û�ˣ���Ҳ�͸���û���ˡ�
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
