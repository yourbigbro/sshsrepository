package service;

import java.sql.SQLException;

import dao.DengLuDao;
import domain.YongHu;

public class DengLuService {

	public boolean dengLu(String parameter, String parameter2) throws SQLException {
		boolean boo=true;
		DengLuDao dld=new DengLuDao();
		YongHu yh=dld.dengLuChaXun(parameter,parameter2);
		if(yh==null){
			boo=false;
		}
		return boo;
	}

}
