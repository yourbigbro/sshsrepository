package test;
//����1��Timer�������ʹ��
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TestTimer {
	public static void main(String[] args) {
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			
			@Override
			public void run() {
				System.out.println(new Date().toLocaleString());
				
			}
		}, 0, 3000);
	}
}
