package com.mcwilliams.urlshortener.redis.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.mcwilliams.urlshortener.redis.model.URL;

@Repository
public interface URLRepo extends CrudRepository<URL, String> {
}
