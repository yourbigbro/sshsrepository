package com.itheima;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;



//˫�������ң����̣߳�
public class ReadRunnable implements Runnable {
	Socket socket;
	public ReadRunnable() {
		
		}
	public ReadRunnable(Socket socket) {
		this.socket=socket;//��ȡ�׽���
		}
	@Override
	public void run() {
		OutputStream os = null;
		InputStream is=null;//������������Ϊȫ�ֱ���
		try {
			is = socket.getInputStream();//��ȡ�׽��ֵ�������
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String str="";
		try {
			while(true){//��ѭ�������߳���Զ����������Զ���Խ�����Ϣ
				int len;
				byte[] by=new byte[1024];
				int read = is.read(by);//��ȡ��Ϣ
				System.out.println(new String(by,0,read));//���
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			is.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
