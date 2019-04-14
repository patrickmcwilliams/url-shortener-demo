package com.mcwilliams.urlshortener.redis.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("Url")
public class URL implements Serializable {

	@Id
	private String fullUrl;
	private String urlHash;

	public URL(String fullUrl, String urlHash) {
		super();
		this.fullUrl = fullUrl;
		this.urlHash = urlHash;
	}
	
	public String getFullUrl() {
		return fullUrl;
	}

	public void setFullUrl(String fullUrl) {
		this.fullUrl = fullUrl;
	}


	public String getUrlHash() {
		return urlHash;
	}



	public void setUrlHash(String urlHash) {
		this.urlHash = urlHash;
	}

	@Override
	public String toString() {
		StringBuilder outString = new StringBuilder();
		outString.append("URL{").append("fullUrl='").append(this.fullUrl).append("', shortUrl='").append(this.urlHash)
				.append("'}");

		return outString.toString();
	}

}
