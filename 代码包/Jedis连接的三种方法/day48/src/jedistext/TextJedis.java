package jedistext;

import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import utils.JedisUtils;

public class TextJedis {
	//单实例连接Redis数据库
	@Test
	public  void textJedis(){
		Jedis jedis = new Jedis("192.168.64.128", 6379);
		jedis.set("张三", "李四");
		String str = jedis.get("张三");
		System.out.println(str);
		jedis.close();
	}
	//连接池连接redis数据库
	@Test
	public void textJedisPool(){
		JedisPoolConfig jpc = new JedisPoolConfig();//获得连接池配置对象并配置连接池
		jpc.setMaxIdle(10);//设置最大空闲线程
		jpc.setMaxTotal(30);//设置最大线程数
		JedisPool jp = new JedisPool(jpc, "192.168.64.128", 6379);
		Jedis jedis= jp.getResource();//创建jedis连接对象
		jedis.set("zhangsan", "lisi");
		String str = jedis.get("zhangsan");
		System.out.println(str);
		jedis.close();//将连接释放到连接池
		jp.close();//关闭连接池
		
	}
	//利用工具类进行连接池连接redis数据库
	@Test
	public void textJedisPoolUtils(){
		Jedis jedis = JedisUtils.getJedis();
		jedis.set("吴林", "啪啪");
		String string = jedis.get("吴林");
		System.out.println(string);
		jedis.close();//将连接对象归还给连接池
		//注意，工具类方法没有销毁连接池
	}
}
