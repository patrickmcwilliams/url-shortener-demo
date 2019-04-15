package com.mcwilliams.urlshortener.repo.jpa;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mcwilliams.urlshortener.model.jpa.URLFull;;

@Repository
public interface URLFullRepo extends CrudRepository<URLFull, String> {
	@Query("FROM URLFull a WHERE a.fullUrl=:url")
    Optional<URLFull> getHashByURL(@Param("url") String url);
	
}
