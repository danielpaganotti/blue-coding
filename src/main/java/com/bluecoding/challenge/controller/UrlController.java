package com.bluecoding.challenge.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bluecoding.challenge.dto.UrlDTO;
import com.bluecoding.challenge.service.UrlService;

@RestController
public class UrlController {

	@Autowired
	private UrlService service;
	
	@PostMapping("/url")
	public String shortUrl(@RequestBody String url) {
		return service.shortenURL(url);
	}
	
	@GetMapping("/url")
	public String getShortUrl(@RequestParam(name = "shortenedUrl") String shortenedUrl) {
		return service.getFullUrl(shortenedUrl);
	}
	
	@GetMapping("/url/top")
	public ResponseEntity<List<UrlDTO>> getTopUrls() {
		
		return ResponseEntity.ok(service.getTopUrls());
	}
	
}
