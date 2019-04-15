package com.mcwilliams.urlshortener.dao;

public interface URLDao {

	public String getHashFromURL(String fullURL);
	public String getURLFromHash(String hash);
	
}
