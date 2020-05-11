package services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.Cryptocurrency;
import model.StatusUpdate;

/*
 * Handles JSON to Java Conversion
 */

public class NewConverterService {

	private final String STATUS_UPDATES = "status_updates";
	private final String COIN_MARKET_URL = "https://api.coingecko.com/api/v3/coins/markets?vs_currency=aud&order=market_cap_desc&per_page=%d&page=1&sparkline=false";
	private final String STATUS_UPDATE_URL = "https://api.coingecko.com/api/v3/coins/%s/status_updates";
	private ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
			false);
	private String url;
	private ApiService apiServer = new ApiService();
	private List<Cryptocurrency> currenciesList;
	private Map<String, List<StatusUpdate>> map = new HashMap<String, List<StatusUpdate>>();

	public NewConverterService(int numberOfCoins) {
		setUrl(String.format(COIN_MARKET_URL, numberOfCoins));
		this.currenciesList = getCoins();
	}

	public List<Cryptocurrency> getCoins() {
		String jsonCoins = apiServer.getJSON(getUrl());
		try {
			currenciesList = objectMapper.readValue(jsonCoins, new TypeReference<List<Cryptocurrency>>() {
			});
			getCoinStatusUpdates(currenciesList);
			setCurrenciesList(currenciesList);
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return currenciesList;
	}

	private List<Cryptocurrency> getCoinStatusUpdates(List<Cryptocurrency> currenciesList) {
		for (Cryptocurrency currency : currenciesList) {
			String currencyUrl = String.format(STATUS_UPDATE_URL, currency.getId());
			String statusUpdatesJSON = apiServer.getJSON(currencyUrl);
			try {
				map = objectMapper.readValue(statusUpdatesJSON,
						new TypeReference<HashMap<String, List<StatusUpdate>>>() {
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

		return currenciesList;
	}

	private String getUrl() {
		return url;
	}

	private void setUrl(String url) {
		this.url = url;
	}

	public List<Cryptocurrency> getCurrenciesList() {
		return currenciesList;
	}

	private void setCurrenciesList(List<Cryptocurrency> currenciesList) {
		this.currenciesList = currenciesList;
	}

	public Cryptocurrency getCryptocurrency(String id) {
		List<Cryptocurrency> list = getCurrenciesList();
		for (Cryptocurrency c : list) {
			if (c.getId().equals(id)) {
				return c;
			}
		}
		return null;
	}
}
