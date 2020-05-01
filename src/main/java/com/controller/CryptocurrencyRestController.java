package com.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.model.Cryptocurrency;
import com.model.Converter;

@RestController
public class CryptocurrencyRestController {

	private final int numberOfCoinsToSearch = 10;
	private final Converter converter = new Converter(numberOfCoinsToSearch);
	private List<Cryptocurrency> listCrypto;

	// For original API all bitcoins
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Cryptocurrency> getCryptocurrency(HttpSession session) {
		// Convert the JSON data from multiple APIs to list of POJOs
//		if (converter.getListCrypto().isEmpty()) {
//			listCrypto = converter.convertJsonToJava();
//			converter.setListCrypto(listCrypto);
//			return listCrypto;
//		} else {
//			return converter.getListCrypto();
//		}
		return converter.getListCrypto();
	}

	// Getting the mapping for by ID
	/*
	 * TODO Maybe make it more efficient for getCryptocurrency? Maybe persist data
	 * instead.. to speed it up.
	 */

	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Cryptocurrency getCryptocurrency(@PathVariable String id) {
		return converter.getCryptocurrency(id);
	}

}
