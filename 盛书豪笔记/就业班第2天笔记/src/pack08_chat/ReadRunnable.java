package pack08_chat;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class ReadRunnable implements Runnable {

	Socket cSocket;

	public ReadRunnable() {
	}

	public ReadRunnable(Socket cSocket) {
		this.cSocket = cSocket;
	}

	@Override
	public void run() {
		try {
			InputStream is = cSocket.getInputStream();//老师可能应该关闭流。
			byte[] bys = new byte[1024];
			int len;
			while (true) {
				// 读取数据
				len = is.read(bys);
				System.out.println("读取数据:" + new String(bys, 0, len));//将读到的字节数组转换成字符串
			}
		} catch (IOException e) {
		}

	}

}
