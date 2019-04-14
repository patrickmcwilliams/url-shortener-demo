package com.mcwilliams.urlshortener.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import com.mcwilliams.urlshortener.redis.config.CacheConfig;
import com.mcwilliams.urlshortener.redis.repo.URLRepo;

public class RedisDao {
	
	@Autowired
	private URLRepo redisCrud;
	@Autowired
	private CacheConfig redisConfig;
	
	private RedisTemplate<String, Object> redisRaw;
	
	public RedisDao() {
		this.redisRaw = this.redisConfig.redisTemplate();
	}
	
	public String getHashFromURL(String fullURL) {

		return null;
	}
	
	static public String getURLFromHash() {
		
		return null;
	}
	
}
