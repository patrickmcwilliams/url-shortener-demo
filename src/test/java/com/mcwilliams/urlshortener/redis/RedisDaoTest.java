package com.mcwilliams.urlshortener.redis;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisDaoTest {

	@Autowired
	RedisDao redis;
	
	@Test
	public void testGetHashFromURL() {
		
		String testUrl = "www.test.com";
		redis.getHashFromURL(testUrl);
	}

	@Test
	public void testGetURLFromHash() {
		//fail("Not yet implemented");
	}

}
