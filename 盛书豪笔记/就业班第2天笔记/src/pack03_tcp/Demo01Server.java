package pack03_tcp;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Demo01Server {

	public static void main(String[] args) throws IOException {
		//1:创建ServerSocket对象,并指定端口号
		ServerSocket sSocket = new ServerSocket(9999);
		
		System.out.println("正在等待客户端请求.......");
		//2:如果客户端有请求，则接受客户端的请求
		//没有请求，该方法阻塞
		//返回的这个socket表示已经建立好的连接,这个socket还保存了客户端的信息
		Socket cSocket = sSocket.accept();
		
		
		//如果程序执行到这里，说明有客户端已经发起了请求，而且已经建立链接
		 //3:服务器要读取数据，则先从建立好的通道中获取输入流
		InputStream is = cSocket.getInputStream();
		
		//4:读取数据
		byte[] bys = new byte[1024];
		int len = is.read(bys);
		
		//获取客户端的ip
		InetAddress inetAddress = cSocket.getInetAddress();
		String ip = inetAddress.getHostAddress();
		//获取端口
		int port = cSocket.getPort();
		//5:打印数据
		
		System.out.println(ip+":"+new String(bys, 0, len));
		
		
		//6:关闭资源
		sSocket.close();
		cSocket.close();
		is.close();
	}

}
