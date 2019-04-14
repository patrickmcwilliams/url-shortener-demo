package com.mcwilliams.urlshortener.redis;

import org.springframework.stereotype.Component;

public interface RedisDao {

	public String getHashFromURL(String fullURL);
	public String getURLFromHash(String hash);
	
}
