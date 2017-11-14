package service;

import java.util.List;

import dao.AjaxDao;
import domain.User;

public class AjaxService {

	public List<User> checkUsername(String value) {
		List<User> user=null;
		if(value==""||value==null){
			return user;
		}
		AjaxDao ad = new AjaxDao();
		user=ad.checkUsername(value);
		return user;
	}

}
