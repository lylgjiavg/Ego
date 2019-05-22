package com.ego.redis.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ego.redis.dao.JedisDao;

import redis.clients.jedis.JedisCluster;

@Repository
public class JedisDaoImpl implements JedisDao {
	
	@Autowired
	private JedisCluster jedisClients;
	
	@Override
	public Boolean exist(String key) {
		
		return jedisClients.exists(key);
	}

	@Override
	public Long del(String key) {
		
		return jedisClients.del(key);
	}

	@Override
	public String set(String key, String value) {
		
		return jedisClients.set(key, value);
	}

	@Override
	public String get(String key) {
		
		return jedisClients.get(key);
	}

}
