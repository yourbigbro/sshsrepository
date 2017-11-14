package web.servlet;

import java.sql.SQLException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import domain.User;
import service.SendMailService;
import utils.EmailUtils;
//������д��EmailUtils���棬�ռ��������ݿ�����
public class SendMailListener implements ServletContextListener {

    
    public SendMailListener() {
        //ʲô������д��û������ղι��캯��Ҳ��
    }

	
    public void contextDestroyed(ServletContextEvent arg0)  { 
         
    }

	
    public void contextInitialized(ServletContextEvent arg0)  { 
         Timer timer = new Timer();
         timer.scheduleAtFixedRate(new TimerTask() {
			
			@Override
			public void run() {
				SendMailService sms = new SendMailService();
				List<User> users = null;
				try {
					users = sms.sendMail();
				} catch (SQLException e) {
					throw new RuntimeException(e);
				}//�������û���ɸѡ����������յ��û�����Ҫ�õ�DateUtils�����ࣩ
				if(users!=null && users.size()>0){
					for (User user : users) {
						EmailUtils.send(user);//����������յ��û�����Ϣ
					}
					System.out.println("�������˹����գ��ʼ��Ѿ��������");
				}
			}
		}, 0, 10000);//ÿ��ʮ�뷢��һ���ʼ�
    }
	
}
