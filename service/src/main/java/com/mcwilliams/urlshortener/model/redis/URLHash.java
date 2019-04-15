package com.mcwilliams.urlshortener.model.redis;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("UrlHash")
public class URLHash implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	private String urlHash;
	private String fullUrl;

	public URLHash() {
	}

	public URLHash(String fullUrl, String urlHash) {
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
