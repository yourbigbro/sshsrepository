package com.itheima;
/*TCP�ļ�����
Ҫ��:
�ͻ��˴ӷ�����������ͼƬ
        ��������d:\\upload1.jpg�ļ���������ʽд���ͻ���
        �ͻ��˴ӷ�������ȡ�ļ�,�����浽c:\downloadĿ¼��
        ����ɹ���,����̨��ӡ���������*/
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class ZuoYe31 {

	public static void main(String[] args) throws IOException {
		// �ͻ���
		InetAddress localHost = InetAddress.getLocalHost();
		String hostAddress = localHost.getHostAddress();
		Socket socket = new Socket(hostAddress,10086);
		OutputStream os = socket.getOutputStream();
		
		InputStream is = socket.getInputStream();
		byte[] by=new byte[1024];
		int len1;
		String sstr="";
		os.write("�뷢���ļ���".getBytes());//�������������������
		/*while((len1=is.read(by))!=-1){//��������
			sstr+=new String(by, 0, len1);
		}*/
		int nlen=is.read(by);
				
		sstr=new String(by, 0, nlen);
		System.out.println(sstr);
		BufferedOutputStream bufos=new BufferedOutputStream(new FileOutputStream("C:\\download\\"+sstr));
		os.write("�������յ�".getBytes());//����������������Ѿ��յ�
		socket.shutdownOutput();
		//System.out.println("_----");
		byte[] bys=new byte[1024];
		int len;
		while((len=is.read(bys))!=-1){//��������
			bufos.write(bys, 0, len);
			bufos.flush();
		}
		//System.out.println("�������");
		bufos.close();
		socket.close();
		
	}

}
