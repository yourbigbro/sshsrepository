package com.itheima;

public class Service {
	static String xiaoxi="ת�˳ɹ�";
	 
		 public static String caoZuo(String name1,String name2,Double money){
				try {
					//��������
					ThreadLocalUtils.start();
					System.out.println("�ɹ���");
					Dao dao=new Dao();
					//���粻��ThreadLocal�Ļ�����Ҫ��con���Ӷ������������뵽queryName�����С�����ThreadLocal�Ͳ��ô������ˣ�����˱�̡�
					Account aa1=dao.queryName(name1);//service�����dao�㶨��ķ��������������̷�װ��һ��������app��ʹ�á�
					Account aa2=dao.queryName(name2);
					aa1.setAmoney(aa1.getAmoney()-money);
					aa2.setAmoney(aa2.getAmoney()+money);
					//���粻��ThreadLocal�Ļ�����Ҫ��con���Ӷ������������뵽queryName�����С�����ThreadLocal�Ͳ��ô������ˣ�����˱�̡�
					dao.updateMoney(name1,money);
					dao.updateMoney(name2,money);
					//�ύ����,����Ļ��ͻع�����
					ThreadLocalUtils.commit();
					return xiaoxi;
				} catch (Exception e) {
					e.printStackTrace();
					//�ع�����
					ThreadLocalUtils.rollback();
					xiaoxi="ת��ʧ��";
					return xiaoxi;
				}
				
				
			}
	
	
}
