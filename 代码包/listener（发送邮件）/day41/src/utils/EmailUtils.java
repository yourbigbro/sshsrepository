package utils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import domain.User;

public class EmailUtils {

    public static void send(User user) {
        try {
            // 1 ���session
            Properties props = new Properties();
            props.setProperty("mail.host", "127.0.0.1");
            // Ϊ��ֹ����Ϊ�����ʼ������������������ǿ��У��
            props.setProperty("mail.smtp.auth", "true");
            Authenticator authenticator = new Authenticator() {
                @Override
                public PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("service@store.com", "123456");//�����ˣ��û��������룩
                }
            };
            Session session = Session.getDefaultInstance(props, authenticator);
            // 2 message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("service@store.com"));//�������û�������ַ��
            message.setRecipient(RecipientType.TO, new InternetAddress(user.getEmail()));
            message.setSubject("ף�����տ���");
            message.setContent("�𾴵Ļ�Ա��ף�����տ��֣�", "text/html;charset=UTF-8");

            // 3����
            Transport.send(message);
            System.out.println("�ʼ����ͳɹ�");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

