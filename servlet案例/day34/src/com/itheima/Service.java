package com.itheima;

import java.sql.SQLException;

public class Service {
	public String dengLu(String username,String password) throws SQLException{
		String  xiaoxi="��½�ɹ�";
		Dao dao=new Dao();
		Account cx = dao.chaXun(username, password);
		if(cx==null){
			xiaoxi="��¼ʧ��";
		}
		return xiaoxi;
	}
}
