package service;

import dao.AjaxDao;
import domain.User;

public class AjaxService {

	public User checkUsername(String value) {
		AjaxDao ad = new AjaxDao();
		User user=ad.checkUsername(value);
		return user;
	}

}
