package com.example.shortenURL.common;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import org.springframework.stereotype.Component;

@Component
public class CommURL {
	public boolean isURLValid(String inputURL){
		try {
			new URL(inputURL).toURI();
		} catch (MalformedURLException | URISyntaxException e) {
			return false;
		}
		
		return true;
	}
}
