package client;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
	RestTemplate restTemplate;

	private final String STATUS_UPDATE_KEY = "status_updates";
	private final String SINGLE_COIN_URL = "https://api.coingecko.com/api/v3/coins/markets?vs_currency=aud&ids=%s&order=market_cap_desc&per_page=100&page=1&sparkline=false";
	private final String MULTIPLE_COINS_URL = "https://api.coingecko.com/api/v3/coins/markets?vs_currency=aud&order=market_cap_desc&per_page=%d&page=1&sparkline=false";
	private final String STATUS_UPDATE_URL = "https://api.coingecko.com/api/v3/coins/%s/status_updates";
	private ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
			false);

	public CoinGeckoClient() {
	}

	// Gets Coin Gecko Coins API
	public List<Cryptocurrency> getCoinGeckoCoinsAPI(int numCoins) {
		String coinsURL = String.format(MULTIPLE_COINS_URL, numCoins);
		String JSON = getJSON(coinsURL);
		List<Cryptocurrency> currenciesList = null;
		try {
			currenciesList = objectMapper.readValue(JSON, new TypeReference<List<Cryptocurrency>>() {
			});
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return currenciesList;
	}

	// Return a Map object from the COIN GECKO API for Status Updates
	public Map<String, List<StatusUpdate>> getCoinGeckoStatusUpdateMapAPI(Cryptocurrency currency) {
		String currencyURL = String.format(STATUS_UPDATE_URL, currency.getId());
		String statusUpdateJSON = getJSON(currencyURL);
		Map<String, List<StatusUpdate>> map = null;
		try {
			map = objectMapper.readValue(statusUpdateJSON, new TypeReference<HashMap<String, List<StatusUpdate>>>() {
			});
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return map;
	}

	// Parse through map to get the individual status updates
	public List<StatusUpdate> getStatusUpdates(Map<String, List<StatusUpdate>> map) {
		return map.get(STATUS_UPDATE_KEY);
	}

	// Gets Coin Gecko coin api for single coin
	public Cryptocurrency getCoinGeckoCoinAPI(String id) {
		String coinURL = String.format(SINGLE_COIN_URL, id);
		String coinJSON = getJSON(coinURL);
		Cryptocurrency c = null;
		List<Cryptocurrency> list = null;
		//
		try {
			list = objectMapper.readValue(coinJSON, new TypeReference<List<Cryptocurrency>>() {
			});
			if (list.isEmpty())
				return null;
			c = list.get(0);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return c;
	}

	private String getJSON(String url) {
		return restTemplate.getForObject(url, String.class);
	}

}
