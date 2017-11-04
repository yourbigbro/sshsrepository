package pack05_tcp_file;


import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class ServerDemo {

	public static void main(String[] args) throws IOException {
		ServerSocket sSocket = new ServerSocket(10086);
		
		Socket cSocket = sSocket.accept();
		
		//获取套接字的输入流
		InputStream is = cSocket.getInputStream();
		//获取文件的输出流
		BufferedOutputStream bufos = 
				new BufferedOutputStream(new FileOutputStream("liutao.jpg"));
		
		//从套接字的输入流读取数据，写入文件的输出流
		byte[] bys  = new byte[1024];
		int len ;
		System.out.println("while之前 ");
		//程序没有结束while循环的原因是没有从套接字的流程读取到-1
	   //套接字的输入流什么时候读取到-1？--->对方付关闭输出流
		while((len = is.read(bys)) != -1){
			bufos.write(bys, 0, len);
			//刷新
			bufos.flush();
		}
		
		System.out.println("反馈信息之前 ");
		//-------------给客户端反馈信息--------------
		OutputStream os = cSocket.getOutputStream();
		os.write("文件上传成功".getBytes());
		System.out.println("反馈信息之后 ");
		
		//关闭资源
		sSocket.close();
		cSocket.close();
		is.close();
		bufos.close();
		
	}

}
