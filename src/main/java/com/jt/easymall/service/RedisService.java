package com.jt.easymall.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

//@Service
public class RedisService {
	@Autowired
	private ShardedJedisPool pool;
	/*
	 * set方法
	 */
	public void set(String key,String value){
		ShardedJedis jedis = pool.getResource();
		jedis.set(key, value);
		pool.returnResource(jedis);
	}
	/*
	 * 设置超时set
	 */
	public void set(String key,String value,Integer second){
		ShardedJedis jedis = pool.getResource();
		jedis.set(key, value);
		jedis.expire(key, second);
		pool.returnResource(jedis);
	}
	
	//判断存在
	public Boolean exists(String key){
		ShardedJedis jedis = pool.getResource();
		Boolean exists=jedis.exists(key);
		pool.returnResource(jedis);
		return exists;
	}
	//获取数据
	public String get(String key){
		ShardedJedis jedis = pool.getResource();
		String value=jedis.get(key);
		pool.returnResource(jedis);
		return value;
	}
	//删除数据
	public void del(String key){
		ShardedJedis jedis = pool.getResource();
		jedis.del(key);
		pool.returnResource(jedis);
	}
}











