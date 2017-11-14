package com.itheima.day49.user.utils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

public class MailUtils {

	/*public static void main(String[] args) {
//		sendMail("xxxx@163.com","11");
		sendMail("damin@store.com","031217");//发件人的用户名和密码
	}*/
	/**
	 * 邮件发送
	 * @param to
	 * @param code
	 */
	public static void sendMail(String to,String code){
		// Session对象:
		Properties props = new Properties();
		//邮件服务器在本机
		props.setProperty("mail.host", "localhost");
		
		//邮件服务器在外网需要用下面两个配置替代 注意，163和QQ等邮箱需要去对应官网注册授权码才可以发送
//		props.setProperty("mail.host", "smtp.163.com");
//		props.setProperty("mail.smtp.auth", "true");
		Session session = Session.getInstance(props, new Authenticator() {
			@Override
			public PasswordAuthentication getPasswordAuthentication() {
				//邮件服务器在本机
				return new PasswordAuthentication("damin@store.com", "031217");//发件人的用户名和密码
				
				
				//邮件服务器在外网
//				return new PasswordAuthentication("******@163.com", "1qaz2wsx");
			}
			
		});
		// Message对象:
		Message message = new MimeMessage(session);
		// 设置发件人：
		try {
			message.setFrom(new InternetAddress("damin@store.com"));//发件人的用户名
			// 设置收件人:
			message.addRecipient(RecipientType.TO, new InternetAddress(to));
			// 设置主题:
			message.setSubject("来自购物天堂STORE的激活邮件");
			// 设置内容：
			String url = "http://localhost:8080/day49/UserServlet?type=active&code="+code;
			message.setContent("<h1>来自购物天堂STORE的激活邮件！激活请点击以下链接！</h1><h3><a href='"+url+"'>"+url+"</a></h3>","text/html;charset=UTF-8");
			// Transport对象:
			Transport.send(message);
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
	}

}
