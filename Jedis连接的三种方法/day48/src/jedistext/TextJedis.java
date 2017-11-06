package jedistext;

import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import utils.JedisUtils;

public class TextJedis {
	//��ʵ������Redis���ݿ�
	@Test
	public  void textJedis(){
		Jedis jedis = new Jedis("192.168.64.128", 6379);
		jedis.set("����", "����");
		String str = jedis.get("����");
		System.out.println(str);
		jedis.close();
	}
	//���ӳ�����redis���ݿ�
	@Test
	public void textJedisPool(){
		JedisPoolConfig jpc = new JedisPoolConfig();//������ӳ����ö����������ӳ�
		jpc.setMaxIdle(10);//�����������߳�
		jpc.setMaxTotal(30);//��������߳���
		JedisPool jp = new JedisPool(jpc, "192.168.64.128", 6379);
		Jedis jedis= jp.getResource();//����jedis���Ӷ���
		jedis.set("zhangsan", "lisi");
		String str = jedis.get("zhangsan");
		System.out.println(str);
		jedis.close();//�������ͷŵ����ӳ�
		jp.close();//�ر����ӳ�
		
	}
	//���ù�����������ӳ�����redis���ݿ�
	@Test
	public void textJedisPoolUtils(){
		Jedis jedis = JedisUtils.getJedis();
		jedis.set("����", "žž");
		String string = jedis.get("����");
		System.out.println(string);
		jedis.close();//�����Ӷ���黹�����ӳ�
		//ע�⣬�����෽��û���������ӳ�
	}
}
