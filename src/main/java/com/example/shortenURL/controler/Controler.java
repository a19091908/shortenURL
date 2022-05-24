package com.example.shortenURL.controler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.shortenURL.obj.URL;
import com.example.shortenURL.service.Impl.ShortenServiceImpl;

@Controller
public class Controler {
	
	final static public String REDIRECT = "redirect";
	
	@Autowired
	ShortenServiceImpl shortenServiceImpl;

	@GetMapping(path = { "", "/", "/index" })
	public String index() {
		return "index";
	}
	
	@PostMapping(path = "/submit")
	public String submit(HttpServletRequest request, final URL url, final Model model) {
		
		String shortURL = shortenServiceImpl.generateShortURL(url);
		if (shortURL != null) {
			shortURL = shortenServiceImpl.getFullURL(shortURL, request);
			model.addAttribute("shortURL", shortURL);
		}else {
			model.addAttribute("shortURL", "");
		}
		
		return "index";
	}
	
	@GetMapping(path = "/redirect/{hash}")
	public ModelAndView redirect(@PathVariable String hash, final Model model) {
		String originalURL = shortenServiceImpl.getOriginalURL(hash);	
		if (originalURL == null) {
			model.addAttribute("shortURL", "");
			return new ModelAndView("index");
		}
		return new ModelAndView("redirect:" + originalURL);
	}
}
