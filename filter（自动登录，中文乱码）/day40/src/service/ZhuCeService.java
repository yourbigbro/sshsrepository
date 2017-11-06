package service;

import dao.ZhuCeDao;

public class ZhuCeService {

	public boolean zhuCe(String parameter, String parameter2) {
		boolean boo=true;//返回true代表用户名已经存在，也就是注册失败
		ZhuCeDao zcd=new ZhuCeDao();
		boo=zcd.zhuCe(parameter,parameter2);
		return boo;
	}

}
