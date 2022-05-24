package com.example.shortenURL.service.Impl;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

	public String getOriginalURL(String hash) {
		Optional<URL> urlOptional = urlDAO.findById(hash);
		return urlOptional.isPresent() ? urlOptional.get().getOriginalURL() : null;
	}

	public String getFullURL(String shortURL, HttpServletRequest request) {
		StringBuilder sb = new StringBuilder();
		sb.append(request.getScheme())
		  .append("://")
		  .append(request.getServerName())
		  .append(":")
		  .append(request.getServerPort())
		  .append(request.getContextPath())
		  .append("/").append(com.example.shortenURL.controler.Controler.REDIRECT)
		  .append("/").append(shortURL);
		return sb.toString();
	}
}
