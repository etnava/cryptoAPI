package com.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model.Cryptocurrency;

import services.NewConverterService;

@RestController()
@RequestMapping("/api")
public class RestTemplateController {

	private final int numOfCoins = 10;
	private final NewConverterService converterService = new NewConverterService(numOfCoins);

	@GetMapping(path = "/allcoins", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Cryptocurrency> getList() {
		return converterService.getCurrenciesList();
	}

	// Getting the mapping for by ID
	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Cryptocurrency getCryptocurrency(@PathVariable String id, HttpServletResponse response) {
		Cryptocurrency crypto = converterService.getCryptocurrency(id);
		if (crypto == null) {
			response.setStatus(400);
		}
		return converterService.getCryptocurrency(id);
	}
}
