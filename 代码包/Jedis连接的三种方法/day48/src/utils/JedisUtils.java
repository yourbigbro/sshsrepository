package utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@SuppressWarnings("resource")
public class JedisUtils {
	static JedisPool jp;
	static{
		JedisPoolConfig jpc = new JedisPoolConfig();
		jpc.setMaxIdle(10);
		jpc.setMaxTotal(30);
		jp = new JedisPool(jpc, "192.168.64.128", 6379);
		
	}
	public static Jedis getJedis(){
		Jedis resource = jp.getResource();
		return resource;
	}
}
