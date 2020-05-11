package controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import model.Cryptocurrency;
import model.StatusUpdate;
import services.ConverterService;

@RestController()
@RequestMapping("/cryptoapi")
public class CryptocurrencyRestController {

	private final int numberOfCoinsToSearch = 10;
	private final ConverterService converter = new ConverterService(numberOfCoinsToSearch);

	
	// For original API all currencies
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Cryptocurrency> getCryptocurrency() {
		return converter.getAll();
	}

	// Getting the mapping for by ID
	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Cryptocurrency getCryptocurrency(@PathVariable String id, HttpServletResponse response) {
		Cryptocurrency crypto = converter.getCryptocurrency(id);
		if (crypto == null) {
			response.setStatus(400);
		}
		return converter.getCryptocurrency(id);
	}

}
