package com.mcwilliams.urlshortener;

import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import com.mcwilliams.urlshortener.model.jpa.URLFull;
import com.mcwilliams.urlshortener.model.redis.URLHash;
import com.mcwilliams.urlshortener.repo.jpa.URLFullRepo;
import com.mcwilliams.urlshortener.repo.redis.URLHashRepo;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UrlShortenerApplicationTests {
	private final String testHash = "abcdefg123";
	private final String testURL = "www.test.com";
	private final String DOESNT_EXIST = "doesnt_exist";
	
	@LocalServerPort
	private int port;
	@Autowired
	private TestRestTemplate testRestTemplate;
	
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
	public void onShorten200() {
		@SuppressWarnings("rawtypes")
		ResponseEntity<String> entity = this.testRestTemplate.getForEntity(
				"http://localhost:" + this.port + "/c?url="+this.testURL, String.class);

		assert( entity.getStatusCode() == HttpStatus.OK);
	}

	@Test
	public void onRedirect302() {
		@SuppressWarnings("rawtypes")
		ResponseEntity<String> entity = this.testRestTemplate.getForEntity(
				"http://localhost:" + this.port + "/c?url="+this.testURL, String.class);
		String shortUrl = "http:"+entity.getBody().replaceAll("^\\<a href\\='|'\\>Link\\<\\/a\\>$", "");
		entity = this.testRestTemplate.getForEntity(shortUrl, String.class);
		assert( entity.getStatusCode() == HttpStatus.FOUND);
	}
	
}
