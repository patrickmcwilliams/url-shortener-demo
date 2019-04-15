package com.mcwilliams.urlshortener.dao;

import org.springframework.stereotype.Component;

public interface URLDao {

	public String getHashFromURL(String fullURL);
	public String getURLFromHash(String hash);
	
}
