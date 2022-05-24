package com.example.shortenURL.service.Impl;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shortenURL.common.CommCode;
import com.example.shortenURL.model.URL;
import com.example.shortenURL.service.repository.UrlDAO;

@Service
public class ShortenServiceImpl {
	static final public int HASH_LEN = 6;
	
	@Autowired
	UrlDAO urlDAO;
	
	@Autowired
	CommCode commCode;
	
	public String generateShortURL(URL url) {
		String shortURL = null;
		try {
			String base64ShortURL = commCode.convertByPaddingAndMD5AndBase64(url.getOriginalURL());
			shortURL = commCode.chooseRandomCharacters(HASH_LEN, base64ShortURL);
			if (urlDAO.existsById(shortURL)) {
				int times = 0;
				boolean isUnique = false;
				while (isUnique == false && times < 10) {
					shortURL = commCode.chooseRandomCharacters(HASH_LEN, base64ShortURL);
					isUnique = urlDAO.existsById(shortURL) == false;
					times++;
				}
				if (isUnique == false) return null;
			}
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
