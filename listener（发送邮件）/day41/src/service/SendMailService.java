package service;

import java.sql.SQLException;
import java.util.List;

import dao.SendMailDao;
import domain.User;

public class SendMailService {
	//筛选出今天过生日的user对象（用户）
	public List<User> sendMail() throws SQLException {
		SendMailDao smd = new SendMailDao();
		List<User> users=smd.sendMail();
		return users;
	}
	
}
