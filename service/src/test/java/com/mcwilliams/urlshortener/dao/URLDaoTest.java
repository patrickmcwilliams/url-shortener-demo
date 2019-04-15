package com.mcwilliams.urlshortener.dao;

import static org.junit.Assert.*;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.mcwilliams.urlshortener.dao.URLDao;
import com.mcwilliams.urlshortener.model.jpa.URLFull;
import com.mcwilliams.urlshortener.model.redis.URLHash;
import com.mcwilliams.urlshortener.repo.jpa.URLFullRepo;
import com.mcwilliams.urlshortener.repo.redis.URLHashRepo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class URLDaoTest {
	
	private final String testHash = "abcdefg123";
	private final String testURL = "www.test.com";
	private final String DOESNT_EXIST = "doesnt_exist";
	
	@Autowired
	URLDao dao;
	
	@MockBean
	private URLHashRepo redisCrud;
	@MockBean
	private URLFullRepo dbCrud;
	
	@Before
	public void setup() {
		Optional<URLHash> urlHashOptionalObject = Optional.of( new URLHash(this.testURL, this.testHash) );
		Optional<URLFull> urlFullOptionalObject = Optional.of( new URLFull(this.testURL, this.testHash) );
		Mockito.when(redisCrud.existsById(this.testHash)).thenReturn(false);
		Mockito.when(redisCrud.findById(this.testHash)).thenReturn(urlHashOptionalObject);
		Mockito.when(redisCrud.findById(this.DOESNT_EXIST)).thenReturn(Optional.empty());
		Mockito.when(dbCrud.getHashByURL(this.testURL)).thenReturn(urlFullOptionalObject);
		
	}
	
	@Test
	public void testGetHashFromURL() {		
		String urlHash = dao.getHashFromURL(this.testURL);
		String urlHash2 = dao.getHashFromURL(this.testURL);
		assert urlHash.equals(urlHash2);
	}

	@Test
	public void testGetURLFromHash() {
		String urlHash = dao.getHashFromURL(this.testURL);
		String testUrl2 = dao.getURLFromHash(urlHash);
		assert(this.testURL.equals(testUrl2));
	}
	
	@Test
	public void testGetURLFromHash404() {
		String testUrl2 = dao.getURLFromHash(this.DOESNT_EXIST);
		assert(testUrl2.equals("404"));
	}
}
