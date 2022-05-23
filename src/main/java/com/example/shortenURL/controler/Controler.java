package com.example.shortenURL.controler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;

@Controller
public class Controler {

	@GetMapping("/")
	public String index() {
		return "index";
	}
}
