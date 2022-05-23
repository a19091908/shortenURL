package com.example.shortenURL.controler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.shortenURL.obj.URL;

@Controller
public class Controler {

	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@PostMapping(value = "/submit")
	public String submit(URL url) {
		System.out.println(url.getOriginalURL());
		return "index";
	}
}
