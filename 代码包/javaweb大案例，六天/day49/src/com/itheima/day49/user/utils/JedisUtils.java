package com.itheima.day49.user.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisUtils {

	
	//连接池
	private static JedisPool pool;
	//在ServletContextListener 的 销毁方法中，可以销毁pool   pool.close()
	//由连接池对象获得连接池再由getResource()方法获得连接对象。
	
	static{
		//初始化
		//Jedis连接池配置
		JedisPoolConfig config = new JedisPoolConfig();
		
		//以下配置是非必须的，不设置就会走默认的
		//空闲时的最大连接数
		config.setMaxIdle(50);
		//空闲时的最小连接数
		config.setMinIdle(10);
		//连接池的最大容量 5000
		config.setMaxTotal(5000);
		pool=new JedisPool(config,"192.168.64.128",6379);//注意配置文件里的Ip地址要修改
		
	}
	
	/**
	 * 获取Jedis链接对象
	 * 
	 */
	public static Jedis getJedis(){
		return pool.getResource();
	}
	
	/**
	 * 获取连接池
	 */
	public static JedisPool getJedisPool(){
		return pool;
	}
	
	/**
	 * 归还链接(注意是归还不是销毁)
	 */
	public static void close(Jedis jedis){
		if(jedis!=null)
			jedis.close();
	}
	
}
