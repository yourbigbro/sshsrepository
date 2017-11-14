package com.itheima;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;



//双人聊天室（多线程）
public class ReadRunnable implements Runnable {
	Socket socket;
	public ReadRunnable() {
		
		}
	public ReadRunnable(Socket socket) {
		this.socket=socket;//获取套接字
		}
	@Override
	public void run() {
		OutputStream os = null;
		InputStream is=null;//将输入流设置为全局变量
		try {
			is = socket.getInputStream();//获取套接字的输入流
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String str="";
		try {
			while(true){//死循环，该线程永远不结束，永远可以接受信息
				int len;
				byte[] by=new byte[1024];
				int read = is.read(by);//读取信息
				System.out.println(new String(by,0,read));//输出
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
