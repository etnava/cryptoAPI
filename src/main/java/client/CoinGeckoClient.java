package client;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.Cryptocurrency;
import model.StatusUpdate;

/*
 * Handles API Calling, gets the JSON Data
 */

public class CoinGeckoClient {

	private RestTemplate restTemplate = new RestTemplate();
	private final String STATUS_UPDATES = "status_updates";
	private final String COIN_MARKET_URL = "https://api.coingecko.com/api/v3/coins/markets?vs_currency=aud&order=market_cap_desc&per_page=%d&page=1&sparkline=false";
	private final String STATUS_UPDATE_URL = "https://api.coingecko.com/api/v3/coins/%s/status_updates";
	private ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
			false);
	private List<Cryptocurrency> currenciesList;

	public List<Cryptocurrency> getCurrenciesList() {
		return currenciesList;
	}

	public void setCurrenciesList(List<Cryptocurrency> currenciesList) {
		this.currenciesList = currenciesList;
	}

	private Map<String, List<StatusUpdate>> map = new HashMap<String, List<StatusUpdate>>(); // getAll, coin.geckoclient
																								// getStatusUpdates
																								// getCoins
	// GetAll Function to call both API

	public CoinGeckoClient() {
	}

	public List<Cryptocurrency> getCoins(int numCoins) {
		String coinsURL = String.format(COIN_MARKET_URL, numCoins);
		String JSON = getJSON(coinsURL);
		try {
			setCurrenciesList(objectMapper.readValue(JSON, new TypeReference<List<Cryptocurrency>>() {
			}));
			updateCoinStatuses();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return getCurrenciesList();
	}

	private void updateCoinStatuses() {
		for (Cryptocurrency currency : getCurrenciesList()) {
			String currencyURL = String.format(STATUS_UPDATE_URL, currency.getId());
			String updateJSON = getJSON(currencyURL);

			try {
				map = objectMapper.readValue(updateJSON, new TypeReference<HashMap<String, List<StatusUpdate>>>() {
				});
				List<StatusUpdate> statusUpdates = map.get(STATUS_UPDATES);
				currency.setStatusUpdates(statusUpdates);
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public String getJSON(String url) {
		return restTemplate.getForObject(url, String.class);
	}

	public Cryptocurrency getCoin(String id) {
		for (Cryptocurrency cryptocurrency : getCurrenciesList()) {
			if (cryptocurrency.getId().equals(id)) {
				return cryptocurrency;
			}
		}
		return null;
	}

}
