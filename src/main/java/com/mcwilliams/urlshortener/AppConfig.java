package com.mcwilliams.urlshortener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericToStringSerializer;

@Configuration
@EnableRedisRepositories(basePackages = "com.mcwilliams.urlshortener.redis.repo")
@PropertySource("classpath:application.properties")
public class AppConfig {

	@Autowired
	Environment env;

	@Bean
	JedisConnectionFactory jedisConnectionFactory() {
		// Get Connection strings
		String redisHost = env.getProperty("redis.host");
		int redisPort = Integer.parseInt( env.getProperty("redis.port") );
		
		// Set config object
		RedisStandaloneConfiguration connectionConfig = new RedisStandaloneConfiguration(redisHost, redisPort);
		
		// Create jedis obj
		JedisConnectionFactory jedisConFactory = new JedisConnectionFactory(connectionConfig);
		return jedisConFactory;
	}

	@Bean
	public RedisTemplate<String, Object> redisTemplate() {
		// Create redis template
		final RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
		template.setConnectionFactory(jedisConnectionFactory());
		template.setValueSerializer(new GenericToStringSerializer<Object>(Object.class));
		return template;
	}

}
