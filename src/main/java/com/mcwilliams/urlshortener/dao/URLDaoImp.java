package com.mcwilliams.urlshortener.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.mcwilliams.urlshortener.model.jpa.URLFull;
import com.mcwilliams.urlshortener.model.redis.URLHash;
import com.mcwilliams.urlshortener.repo.jpa.URLFullRepo;
import com.mcwilliams.urlshortener.repo.redis.URLHashRepo;

import org.apache.commons.lang3.RandomStringUtils;

@Component
public class URLDaoImp implements URLDao {
	
	@Autowired
	private URLHashRepo redisCrud;
	@Autowired
	private URLFullRepo dbCrud;
	private URLHash fourOhFour;
	
	public URLDaoImp() {
		this.fourOhFour = new URLHash("/404","00000");
	}
	
	@Override
	public String getHashFromURL(String fullURL) {
		URLFull urlredis = dbCrud.getHashByURL(fullURL).orElseGet( () -> getNewHash(fullURL) );
		return urlredis.getUrlHash();
	}
	
	@Override
	public String getURLFromHash(String hash) {
		URLHash urlredis = redisCrud.findById(hash).orElse(this.fourOhFour);
		return urlredis.getFullUrl();
	}
	
	private URLFull getNewHash(String fullURL) {
		String newHash = RandomStringUtils.randomAlphanumeric(10);
		while (redisCrud.existsById(newHash)) {
			newHash = RandomStringUtils.randomAlphanumeric(10);
			//TODO: some logging and some n attempts exit
		}
		URLHash newUrlHash = new URLHash(fullURL, newHash);
		URLFull newUrlFull = new URLFull(fullURL, newHash);
		redisCrud.save(newUrlHash);
		dbCrud.save(newUrlFull);
		return newUrlFull;
	}
}
