package com.itheima.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 获得Jedis对象
 * 
 * @author Never Say Never
 * @date 2017年7月25日
 * @version V1.0
 */
public class JedisUtils {

    private static JedisPool pool;

    static {
        // 1.获得连接池配置对象
        JedisPoolConfig config = new JedisPoolConfig();
        // 2.进行相关的配置(不是必须的，有默认的)
        config.setMaxIdle(100);
        // 3.根据连接池配置对象获得连接池对象
        pool = new JedisPool(config, "192.168.59.136", 6379);
    }

    public static Jedis getJedis() {
        return pool.getResource();
    }

}
