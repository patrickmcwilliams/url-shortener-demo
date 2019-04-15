package com.mcwilliams.urlshortener.repo.redis;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mcwilliams.urlshortener.model.redis.URLHash;

@Repository
public interface URLHashRepo extends CrudRepository<URLHash, String> {
}
