package utils;

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

    public static void main(String[] args) {
        sendMail("jjd@store.com", "Mantou9187");
    }

    /**
     * 外网邮件发送
     * 
     * @param to        收件人账号
     * @param code      收件人密码
     */
    public static void sendMail(String to, String code) {
        // 1.Session对象(会话):
        Properties props = new Properties();
        // 邮件服务器在本机
        props.setProperty("mail.host", "localhost");

        // 邮件服务器在外网需要用下面两个配置替代
        // props.setProperty("mail.host", "smtp.163.com");
        // 外网会强制验证
        // props.setProperty("mail.smtp.auth", "true");
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            public PasswordAuthentication getPasswordAuthentication() {
                // 邮件服务器在本机
                return new PasswordAuthentication("service@store.com", "123456");

                // 邮件服务器在外网
                // return new PasswordAuthentication("******@163.com", "1qaz2wsx");
            }

        });
        // 2.Message对象:
        Message message = new MimeMessage(session);
        // 设置发件人：
        try {
            message.setFrom(new InternetAddress("service@store.com"));
            // 设置收件人:
            message.addRecipient(RecipientType.TO, new InternetAddress(to));
            // 设置主题:
            message.setSubject("来自购物天堂STORE的激活邮件");
            // 设置内容：
            String url = "http://localhost:8080/store_v1.0/UserServlet?method=active&code=" + code;
            message.setContent(
                    "<h1>来自购物天堂STORE的激活邮件！激活请点击以下链接！</h1><h3><a href='" + url + "'>" + url + "</a></h3>",
                    "text/html;charset=UTF-8");
            // 3.Transport对象:
            Transport.send(message);
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

}

