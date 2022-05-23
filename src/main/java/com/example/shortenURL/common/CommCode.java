package com.example.shortenURL.common;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class CommCode {
	public byte[] convertByMD5(String originalURL) throws UnsupportedEncodingException, NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
	    md.update(originalURL.getBytes("UTF-8"));
	    return md.digest();
	}

	public String convertByBase64(byte[] originalBytes){
		return Base64.getEncoder().withoutPadding().encodeToString(originalBytes);
	}

}
