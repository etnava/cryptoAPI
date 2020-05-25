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
	CoinService coinService;

	@GetMapping
	public List<Cryptocurrency> getAllCoins() {
		return coinService.getAllCoins();
	}

	@GetMapping(path = "/{id}")
	public Cryptocurrency getCoin(@PathVariable String id, HttpServletResponse response) {
		if (coinService.getCoin(id) == null) {
			response.setStatus(404);
		}
		return coinService.getCoin(id);
	}
}
