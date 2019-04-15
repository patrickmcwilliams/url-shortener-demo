package com.mcwilliams.urlshortener.model.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "url")
public class URLFull {
	@Id
	@Column(name = "url_hash")
	private String urlHash;
	@Column(name = "url_full")
	private String fullUrl;

	public URLFull() {
	}

	public URLFull(String fullUrl, String urlHash) {
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
