package cn.itheima.user.utils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailUtils {

    public static void send(User user) {
        try {
            // 1 获得session
            Properties props = new Properties();
            props.setProperty("mail.host", "127.0.0.1");
            // 为防止被作为垃圾邮件处理，允许服务器进行强制校验
            props.setProperty("mail.smtp.auth", "true");
            Authenticator authenticator = new Authenticator() {
                @Override
                public PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("service@store.com", "123456");
                }
            };
            Session session = Session.getDefaultInstance(props, authenticator);
            // 2 message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("service@store.com"));
            message.setRecipient(RecipientType.TO, new InternetAddress(user.getEmail()));
            message.setSubject("祝福生日快乐");
            message.setContent("尊敬的会员，祝您生日快乐！", "text/html;charset=UTF-8");

            // 3发送
            Transport.send(message);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
