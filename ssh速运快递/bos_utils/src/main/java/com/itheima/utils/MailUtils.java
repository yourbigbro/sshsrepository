package com.itheima.utils;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
//该类为发邮件的工具类
public class MailUtils {
	//设置邮件服务器
	private static String smtp_host = "smtp.163.com"; 
	//发件使用的服务器
	private static String username = "18669726371@163.com"; 
	//该发件服务器的授权码
	private static String password = "qwe123"; 
    //设置发件方
	private static String from = "18669726371@163.com"; // 使用当前账户
	//设置邮件中内容的链接(也就是用户点击链接之后访问的地址)(后面没有加参数，需要结合前台页面的电话号码和后台页面生成的随机数验证码完善该链接)
	public static String activeUrl = "http://localhost:12310/bos_before/customerAction_activeMail.action";

	//该方法用于发送邮件(在action中使用该方法)，以一个参数是邮件主题，第二个参数是邮件内容，第三个参数是收件人邮箱
	public static void sendMail(String subject, String content, String to) {
		Properties props = new Properties();
		props.setProperty("mail.smtp.host", smtp_host);
		props.setProperty("mail.transport.protocol", "smtp");
		props.setProperty("mail.smtp.auth", "true");
		Session session = Session.getInstance(props);
		Message message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(from));
			message.setRecipient(RecipientType.TO, new InternetAddress(to));
			message.setSubject(subject);
			message.setContent(content, "text/html;charset=utf-8");
			Transport transport = session.getTransport();
			transport.connect(smtp_host, username, password);
			transport.sendMessage(message, message.getAllRecipients());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("邮件发送失败...");
		}
	}

	public static void main(String[] args) {
		sendMail("盛书豪测试邮件", "你好", "18669726371@163.com");
	}
}
