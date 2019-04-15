package com.mcwilliams.urlshortener.dao;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mcwilliams.urlshortener.dao.URLDao;

@RunWith(SpringRunner.class)
@SpringBootTest
public class URLDaoTest {

	@Autowired
	URLDao dao;
	
	@Test
	public void testGetHashFromURL() {		
		String testUrl = "www.test.com";
		String urlHash = dao.getHashFromURL(testUrl);
		String urlHash2 = dao.getHashFromURL(testUrl);
		assert urlHash.equals(urlHash2);
	}

	@Test
	public void testGetURLFromHash() {
		String testUrl = "www.test.com";
		String urlHash = dao.getHashFromURL(testUrl);
		String testUrl2 = dao.getURLFromHash(urlHash);
		assert(testUrl.equals(testUrl2));
	}

}
