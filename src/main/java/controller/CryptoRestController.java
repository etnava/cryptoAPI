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

@RestController()
@RequestMapping("/coins")
public class CryptoRestController {

	@Autowired
	CoinService service;
	
	private final int numOfCoins = 10; // Should be part of getCryptocurrency
	private final CoinService converterService = new CoinService(numOfCoins); // Use Autowired, @spring context ,
																				// dependency injection spring

	@GetMapping // remove all coins make it all dont need to specify produces
	public List<Cryptocurrency> getAllCoins() {
		return converterService.getCurrenciesList(); // include number of coins in the server
	}

	// Getting the mapping for by ID
	@GetMapping(path = "/{id}") // dont need to specify produces
	public Cryptocurrency getCoin(@PathVariable String id, HttpServletResponse response) {
		Cryptocurrency crypto = converterService.getCryptocurrency(id);
		if (crypto == null) {
			response.setStatus(404);
		}
		return crypto;
	}

}
