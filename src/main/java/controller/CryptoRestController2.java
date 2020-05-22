package controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import model.Cryptocurrency;
import services.CoinService;
import services.CoinService2;

@RestController()
@RequestMapping("/coins")
public class CryptoRestController2 {

	@Autowired
	CoinService2 service;

	public List<Cryptocurrency> getAllCoins() {
		return service.getEverything();
	}

	// Getting the mapping for by ID
	@GetMapping(path = "/{id}") // dont need to specify produces
	public Cryptocurrency getCoin(@PathVariable String id, HttpServletResponse response) {
		Cryptocurrency crypto = service.getCryptocurrency(id);
		if (crypto == null) {
			response.setStatus(404);
		}
		return crypto;
	}

}
