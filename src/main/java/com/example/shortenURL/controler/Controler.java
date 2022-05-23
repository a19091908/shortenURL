package com.example.shortenURL.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.shortenURL.obj.URL;
import com.example.shortenURL.service.Impl.ShortenServiceImpl;

@Controller
public class Controler {
	
	@Autowired
	ShortenServiceImpl shortenServiceImpl;

	@GetMapping(path = { "", "/" })
	public String index() {
		return "index";
	}
	
	@PostMapping(path = "/submit")
	public String submit(final URL url, final Model model) {
		String shortURL = shortenServiceImpl.generateShortURL(url);
		if (shortURL != null) {
			model.addAttribute("shortURL", shortURL);
		}else {
			model.addAttribute("shortURL", "Fail");
		}
		
		return "index";
	}
}
