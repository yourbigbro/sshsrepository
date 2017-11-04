package pack04_tcp_file;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientDemo {

	public static void main(String[] args) throws Exception, IOException {
		Socket socket = new Socket("192.168.160.82",10086);
		
		//获取套接字的输出流
		OutputStream os = socket.getOutputStream();
		//获取文件的输入流
		BufferedInputStream bufis = new BufferedInputStream(new FileInputStream("刘涛.jpg"));
		
		//从文件的输入流读取数据,写入套接字的输出流
		byte[] bys = new byte[1024];
		int len ;
		while((len = bufis.read(bys)) != -1){
			os.write(bys, 0, len);
		}
		
		//关闭资源
		socket.close();
		os.close();
		bufis.close();
	}

}
