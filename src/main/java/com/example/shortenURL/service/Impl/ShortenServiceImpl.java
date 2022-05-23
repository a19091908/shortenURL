package com.example.shortenURL.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shortenURL.common.CommCode;
import com.example.shortenURL.obj.URL;
import com.example.shortenURL.service.repository.UrlDAO;

@Service
public class ShortenServiceImpl {
	@Autowired
	UrlDAO urlDAO;
	
	public String generateShortURL(URL url) {
		CommCode commCode = new CommCode();
		String shortURL = null;
		try {
			shortURL = commCode.convertByBase64(commCode.convertByMD5(url.getOriginalURL()));
			url.setHash(shortURL);
			urlDAO.saveAndFlush(url);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return shortURL;
	}
}
