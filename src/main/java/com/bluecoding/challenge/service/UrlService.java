package com.bluecoding.challenge.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.bluecoding.challenge.domain.Url;
import com.bluecoding.challenge.dto.UrlDTO;
import com.bluecoding.challenge.repository.UrlRepository;

@Service
public class UrlService {

	@Autowired
	private UrlRepository repo;
	
	public String shortenURL(String fullUrl) {
		Optional<Url> urlEntity = repo.findByUrl(fullUrl);
		return urlEntity
				.map(url -> url.getShortenedUrl())
				.orElseGet(() -> createAndEncodeUrl(fullUrl));
				
	}
	
	public String getFullUrl(String shortenedUrl) {
		return repo.findByShortenedUrl(shortenedUrl)
			.map(url -> {
				url.setAccessCount(url.getAccessCount() + 1);
				repo.save(url);
				return url.getUrl();
			}).orElseThrow();
	}
	
	public List<UrlDTO> getTopUrls() {
		Page<Url> page = repo.findAll(
				PageRequest.of(0, 100, Sort.by(Sort.Direction.DESC, "accessCount")));
		
		return page
				.map(entity -> {
					UrlDTO dto = new UrlDTO();
					dto.setId(entity.getId());
					dto.setUrl(entity.getUrl());
					dto.setShortenedUrl(entity.getShortenedUrl());
					dto.setAccessCount(entity.getAccessCount());
					return dto;
				}).getContent();
	}
	
	private Url createNewUrl(String fullUrl) {
		Url url = new Url();
		url.setUrl(fullUrl);
		url.setAccessCount(0);
		return repo.save(url);		
	}
	
	private String createAndEncodeUrl(String fullUrl) {
		Url newUrlCreated = createNewUrl(fullUrl);
		newUrlCreated.setShortenedUrl(encodeUrl(newUrlCreated.getId()));
		
		repo.save(newUrlCreated);
		return newUrlCreated.getShortenedUrl();
	}
	
	private String encodeUrl(Long id) {
		String baseURL = "https://mydomain.com/"+id;
		return baseURL;
	}
}
