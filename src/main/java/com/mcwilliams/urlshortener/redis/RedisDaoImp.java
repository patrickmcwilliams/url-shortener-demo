package com.mcwilliams.urlshortener.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import com.mcwilliams.urlshortener.redis.model.URL;
import com.mcwilliams.urlshortener.redis.repo.URLRepo;
import org.apache.commons.lang3.RandomStringUtils;

@Component
public class RedisDaoImp implements RedisDao {
	
	@Autowired
	private URLRepo redisCrud;
	@Autowired
	private RedisTemplate<String, Object> redisRaw;
	
	public RedisDaoImp() {}
	
	@Override
	public String getHashFromURL(String fullURL) {
		URL urlredis = redisCrud.findById(fullURL).orElseGet( () -> getNewHash(fullURL) );
		return urlredis.getUrlHash();
	}
	
	@Override
	public String getURLFromHash(String hash) {
		if (redisRaw.boundSetOps("urlHash").isMember(hash)) {
			
		}
		return null;
	}
	
	private URL getNewHash(String fullURL) {
		String newHash = RandomStringUtils.randomAlphanumeric(10);
		while (redisRaw.boundSetOps("urlHash").isMember(newHash)) {
			newHash = RandomStringUtils.randomAlphanumeric(10);
			//TODO: some logging and some n attempts exit
		}
		redisRaw.boundSetOps("urlHash").add(newHash);
		URL newUrl = new URL(fullURL, newHash);
		redisCrud.save(newUrl);
		return newUrl;
	}
}
