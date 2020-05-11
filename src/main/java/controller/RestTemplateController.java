package controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import model.Cryptocurrency;
import services.NewConverterService;

@RestController()
@RequestMapping("/api")
public class RestTemplateController {

	@Autowired
	RestTemplate restTemplate;

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

	// Update Current List of Cryptocurrency
	@GetMapping("/update")
	public List<Cryptocurrency> getUpdate() {
		converterService.getCoins();
		return getList();
	}

	@RequestMapping(value = "/consume")
	public String getApiList() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		return restTemplate.exchange(
				"https://api.coingecko.com/api/v3/coins/markets?vs_currency=aud&order=market_cap_desc&per_page=10&page=1&sparkline=false",
				HttpMethod.GET, entity, String.class).getBody();
	}

}
