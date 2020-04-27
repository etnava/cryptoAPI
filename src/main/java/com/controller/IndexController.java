package com.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.model.Cryptocurrency;

@Controller
public class IndexController {

	@GetMapping("/")
	public String getIndex(Model model) {
		int numberOfCoinsToSearch = 20;
		MapObjects mapObjects = new MapObjects(numberOfCoinsToSearch);
		// Do Conversion of API
		List<Cryptocurrency> listCrypto = mapObjects.convertJsonToJava();
		
		// Convert from JAVA to JSON format and redisplay
		String json = (mapObjects.convertJavaToJson(listCrypto));
		model.addAttribute("json", json);
		return "index";
	}
	
	
	
	// For selecting number of coins.. later..?
	@PostMapping("/numberOfCoinsSearch")
	public String postIndex(Model model) {
		return "index";
	}
	

}
