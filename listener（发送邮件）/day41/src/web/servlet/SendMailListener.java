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
//发件人写在EmailUtils里面，收件人在数据库里面
public class SendMailListener implements ServletContextListener {

    
    public SendMailListener() {
        //什么都不用写，没有这个空参构造函数也行
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
				}//从所有用户中筛选出今天过生日的用户（需要用到DateUtils工具类）
				if(users!=null && users.size()>0){
					for (User user : users) {
						EmailUtils.send(user);//给当天过生日的用户发信息
					}
					System.out.println("今天有人过生日，邮件已经发送完毕");
				}
			}
		}, 0, 10000);//每隔十秒发送一次邮件
    }
	
}
