package com.itheima;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/*ʵ��TCP����
	Ҫ��:
�ͻ��˼���¼�����ݷ��͸���������,¼��һ�з�һ��
    ���������յ��ͻ������ݺ�,������ת�ɴ�д�ظ��ͻ���
    �ͻ��˶�ȡ���������صĴ�д����
    ����ͻ���¼�� over ��ֹͣ����*/
public class ZuoYe22 {

	public static void main(String[] args) throws IOException {
		// �����
		ServerSocket ss = new ServerSocket(10086);//ֻд�˿ںŲ�дip��ַ�ַ���
		Socket accept = ss.accept();
		InputStream is = accept.getInputStream();
		OutputStream os = accept.getOutputStream();
		while(true){
			byte[] by=new byte[1024];
			int len;
			len=is.read(by);
			String str=new String(by, 0, len);
			String str1=str.toUpperCase();
			os.write(str1.getBytes());
			if(str.equals("over")){
				System.out.println("�������");
				break;
			}
		}
		accept.close();//�رտͻ����׽���
		ss.close();//�رշ�����׽���
	}

}
