package service;

import dao.ZhuCeDao;

public class ZhuCeService {

	public boolean zhuCe(String parameter, String parameter2) {
		boolean boo=true;//����true�����û����Ѿ����ڣ�Ҳ����ע��ʧ��
		ZhuCeDao zcd=new ZhuCeDao();
		boo=zcd.zhuCe(parameter,parameter2);
		return boo;
	}

}
