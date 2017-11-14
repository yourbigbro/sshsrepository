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
     * �����ʼ�����
     * 
     * @param to        �ռ����˺�
     * @param code      �ռ�������
     */
    public static void sendMail(String to, String code) {
        // 1.Session����(�Ự):
        Properties props = new Properties();
        // �ʼ��������ڱ���
        props.setProperty("mail.host", "localhost");

        // �ʼ���������������Ҫ�����������������
        // props.setProperty("mail.host", "smtp.163.com");
        // ������ǿ����֤
        // props.setProperty("mail.smtp.auth", "true");
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            public PasswordAuthentication getPasswordAuthentication() {
                // �ʼ��������ڱ���
                return new PasswordAuthentication("service@store.com", "123456");

                // �ʼ�������������
                // return new PasswordAuthentication("******@163.com", "1qaz2wsx");
            }

        });
        // 2.Message����:
        Message message = new MimeMessage(session);
        // ���÷����ˣ�
        try {
            message.setFrom(new InternetAddress("service@store.com"));
            // �����ռ���:
            message.addRecipient(RecipientType.TO, new InternetAddress(to));
            // ��������:
            message.setSubject("���Թ�������STORE�ļ����ʼ�");
            // �������ݣ�
            String url = "http://localhost:8080/store_v1.0/UserServlet?method=active&code=" + code;
            message.setContent(
                    "<h1>���Թ�������STORE�ļ����ʼ������������������ӣ�</h1><h3><a href='" + url + "'>" + url + "</a></h3>",
                    "text/html;charset=UTF-8");
            // 3.Transport����:
            Transport.send(message);
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

}

