package pack03_tcp;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Demo02Client {

	public static void main(String[] args) throws UnknownHostException, IOException {
		//1:创建Socket，并指定服务器的Ip和端口
		//该方法在内部已经进行了三次握手，和服务器建立好连接通道
		Socket socket = new Socket("192.168.160.82", 9999);
		
		//2：获取输出流
		OutputStream os = socket.getOutputStream();
		
		//3:写数据
		os.write("hello".getBytes());
		
		//4:关闭资源
		os.close();
		socket.close();
	}

}
