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
public class CoinRestController {

	@Autowired
	CoinService service;

	@GetMapping
	public List<Cryptocurrency> getAllCoins() {
		return service.getCurrenciesList();
	}

	@GetMapping(path = "/{id}")
	public Cryptocurrency getCoin(@PathVariable String id, HttpServletResponse response) {
		if (service.getCoin(id) == null) {
			response.setStatus(404);
		}
		return service.getCoin(id);
	}
}
