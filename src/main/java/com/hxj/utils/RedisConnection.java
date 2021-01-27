package com.hxj.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @Date: 2021/1/26
 * @Author: 黄先军
 * @Description:
 */
public class RedisConnection {
    private static String HOST = "47.100.88.31";
    private static int PORT = 6379;
    private static int MAX_ACTIVE = 1024;
    private static int MAX_IDLE = 200;
    private static int MAX_WAIT = 10000;

    private static JedisPool jedisPool = null;

    /**
     * 初始化redis连接池
     * */
    private static void initPool(){
        try {
            JedisPoolConfig config = new JedisPoolConfig();
            //最大连接数
            config.setMaxTotal(MAX_ACTIVE);
            //最大空闲连接数
            config.setMaxIdle(MAX_IDLE);
            //获取可用连接的最大等待时间
            config.setMaxWaitMillis(MAX_WAIT);
            jedisPool = new JedisPool(config, HOST, PORT);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 获取jedis实例
     * */
    public synchronized static Jedis getJedis() {
        try {
            if(jedisPool == null){
                initPool();
            }
            Jedis jedis = jedisPool.getResource();
            jedis.auth("123456");
            return jedis;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
