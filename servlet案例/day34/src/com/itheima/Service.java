package com.itheima;

import java.sql.SQLException;

public class Service {
	public String dengLu(String username,String password) throws SQLException{
		String  xiaoxi="µÇÂ½³É¹¦";
		Dao dao=new Dao();
		Account cx = dao.chaXun(username, password);
		if(cx==null){
			xiaoxi="µÇÂ¼Ê§°Ü";
		}
		return xiaoxi;
	}
}
