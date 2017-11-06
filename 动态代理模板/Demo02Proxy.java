package pack08_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
//��̬����Ĳ��裨��̬�����õ�����������ͷ����֪ʶ��
class MyInvocationHandler implements InvocationHandler{//û��public

	Sing obj; //singer����
	
	public MyInvocationHandler() {
	}
	

	public MyInvocationHandler(Sing obj) {
		this.obj = obj;
	}

	/*
	 *����1:  ���������(�����˶���),�������޹�
	 *����2��  ��Ҫ��ǿ���ĸ���������:sing(); --->Method method = getMethod("sing");
	 *����3��  Ҫ����ǿ�ķ������ݵĲ���:sing()
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {//��ǿ����д��invoke()������ֵ��Ȼ��Object
		
		System.out.println("������");
		//�ȵ���û����ǿ֮ǰ�ķ���
		Object result = method.invoke(obj, args);//����ֵ��Object
		
		System.out.println("�����");
		
		return result; //ԭ���ķ�������ʲô�����ﻹ����ʲô
	}
	
}
public class Demo02Proxy {//��public

	public static void main(String[] args) {
		//��̬����֮ǰ
		Sing singer01 = new Singer01();
		singer01.sing();//����ķ���
		
		//ʹ�ô�����Ը�����ĳ�����Ϊ���й�����ǿ
		/*
		 * ����1������������������
		 * ����2����������ʵ�ֵĽӿ�
		 */

		System.out.println("---------------");
		singer01 = myProxy(singer01);//���������ǿ
		singer01.sing();//������ǿ��ķ���
		
		System.out.println("---------------------");
		Sing singer02 = new Singer02();
		singer02 = myProxy(singer02);//���������ǿ
		singer02.sing();//������ǿ��ķ���
	}
	
	public static Sing myProxy(Sing singer){//������ǿ�ķ������������涨�壩������Ĳ������ͺͷ���ֵ���Ͷ���һ���ģ�������Sing��
		Sing proxySinger = (Sing)Proxy.newProxyInstance(singer.getClass().getClassLoader(), //���������������//�ǵý�����ֵǿ��ת�ͳ�Sing
	               singer.getClass().getInterfaces(),//�������ʵ�ֵĽӿ�
	               new MyInvocationHandler(singer));//ʹ���˸�ʵ��InvocationHandler�ӿڵ����ʵ������//�������������������ļ�ǿ��ĵط�
		
		return proxySinger;
	}

}
