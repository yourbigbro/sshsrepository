package service;

import java.sql.SQLException;
import java.util.List;

import dao.SendMailDao;
import domain.User;

public class SendMailService {
	//ɸѡ����������յ�user�����û���
	public List<User> sendMail() throws SQLException {
		SendMailDao smd = new SendMailDao();
		List<User> users=smd.sendMail();
		return users;
	}
	
}
