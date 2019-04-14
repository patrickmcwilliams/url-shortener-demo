package com.mcwilliams.urlshortener.redis.model;

import java.io.Serializable;

import org.springframework.data.redis.core.RedisHash;

@RedisHash("Url")
public class URL implements Serializable {

	private String fullUrl;
	private String urlHash;

	public URL(String fullUrl, String shortUrl) {
		this.fullUrl = fullUrl;
		this.urlHash = shortUrl;
	}

	public String getFullUrl() {
		return fullUrl;
	}

	public void setFullUrl(String fullUrl) {
		this.fullUrl = fullUrl;
	}

	public String getShortUrl() {
		return urlHash;
	}

	public void setShortUrl(String shortUrl) {
		this.urlHash = shortUrl;
	}

	@Override
	public String toString() {
		StringBuilder outString = new StringBuilder();
		outString.append("URL{").append("fullUrl='").append(this.fullUrl).append("', sortUrl='").append(this.urlHash)
				.append("'}");

		return outString.toString();
	}

}
