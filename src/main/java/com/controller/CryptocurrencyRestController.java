package com.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.model.Cryptocurrency;
import com.model.Converter;


@RestController
public class CryptocurrencyRestController {

	@GetMapping(value = "/{cryptocurrency}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Cryptocurrency> getCryptocurrency() {
		int numberOfCoinsToSearch = 10;
		Converter mapObjects = new Converter(numberOfCoinsToSearch);
		// Convert the JSON data from multiple APIs to list of POJOs
		List<Cryptocurrency> listCrypto = mapObjects.convertJsonToJava();
		return listCrypto;
	}
	
}
