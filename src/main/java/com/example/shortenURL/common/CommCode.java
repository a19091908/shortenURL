package com.example.shortenURL.common;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class CommCode {
	
	static Random random = new Random();
	
	public String convertByPaddingAndMD5AndBase64(String originalURL) throws UnsupportedEncodingException, NoSuchAlgorithmException {
		return convertByBase64(convertByMD5(originalURL + getRandomStr()));
	}
	
	public byte[] convertByMD5(String originalURL) throws UnsupportedEncodingException, NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
	    md.update(originalURL.getBytes("UTF-8"));
	    return md.digest();
	}

	public String convertByBase64(byte[] originalBytes){
		return Base64.getUrlEncoder().withoutPadding().encodeToString(originalBytes);
	}

	public String chooseRandomCharacters(int number, String shortURL) {
		StringBuilder sb = new StringBuilder(number);
		for (int i = 0 ; i < number ; i++) {
			sb.append(shortURL.charAt(random.nextInt(shortURL.length())-1));
		}
		return sb.toString();
	}
	
	public String getRandomStr() {
		return "" + random.nextInt(1000);
	}

}
