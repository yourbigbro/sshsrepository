package com.itheima;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
/*TCP�ļ�����
Ҫ��:
�ͻ��˴ӷ�����������ͼƬ
        ��������d: upload1 .jpg�ļ���������ʽд���ͻ���
        �ͻ��˴ӷ�������ȡ�ļ�,�����浽c:\downloadĿ¼��
        ����ɹ���,����̨��ӡ���������*/
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ZuoYe32 {

	public static void main(String[] args) throws IOException {
		// �����
		ServerSocket ss = new ServerSocket(10086);
		Socket accept = ss.accept();
		//�����Ƚ��տͻ������ݱ�������
		InputStream is = accept.getInputStream();
		int read = is.read(new byte[1024]);//
		OutputStream os = accept.getOutputStream();
		
		File fi=new File("D:\\upload1.jpg");
		BufferedInputStream bufis=new BufferedInputStream(new FileInputStream(fi));
		String name = fi.getName();
		os.write(name.getBytes());//��������
		is.read(new byte[1024]);//���ܿͻ��˷����ķ���
		System.out.println("1111222");
		int len;
		byte[] by=new byte[1024];
		while((len=bufis.read(by))!=-1){//��������
			os.write(by,0,len);
		}
		accept.shutdownOutput();///////////////
		bufis.close();
		ss.close();
	}

}
