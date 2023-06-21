package com.bluecoding.challenge.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.bluecoding.challenge.domain.Url;


/**
 *
 * @author daniel
 */
public interface UrlRepository extends JpaRepository<Url, Long> {
    
	public Optional<Url> findByUrl(String url);
	
	public Optional<Url> findByShortenedUrl(String shortenedUrl);
}
