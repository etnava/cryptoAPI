package client;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

public class ApiClient2 {

	private RestTemplate restTemplate = new RestTemplate();
	private final String STATUS_UPDATES = "status_updates";
	private final String COIN_MARKET_URL = "https://api.coingecko.com/api/v3/coins/markets?vs_currency=aud&order=market_cap_desc&per_page=%d&page=1&sparkline=false"; // Move
	private final String STATUS_UPDATE_URL = "https://api.coingecko.com/api/v3/coins/%s/status_updates"; // Move to
	private ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
			false); // Move to Client
	private String url;
	private int numCoins;
	private Map<String, List<StatusUpdate>> map = new HashMap<String, List<StatusUpdate>>(); // getAll, coin.geckoclient
																								// getStatusUpdates
																								// getCoins
	// GetAll Function to call both API

	public ApiClient2(int numCoins) {
		this.numCoins = numCoins;
	}

	public int getNumCoins() {
		return numCoins;
	}

	public void setNumCoins(int numCoins) {
		this.numCoins = numCoins;
	}


	/*
	 * Converts the API url as a String
	 */
	public String getJSON(String url) {
		return restTemplate.getForObject(url, String.class);
	}

	private void convertJsonToJava() { // Get All Coins and Get ID
		String jsonCoins = client.getJSON(getUrl()); // do it client.getCoins pass coins here return, Client has to
														// return Java File type
		try {
			// Initial Set Currencies list (No status Updates)
			setCurrenciesList(objectMapper.readValue(jsonCoins, new TypeReference<List<Cryptocurrency>>() {
			}));

			// Replace current currencies list with updated currencies list with status
			// updates
			setCurrenciesList(updateCoinStatuses(getCurrenciesList()));
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private List<Cryptocurrency> updateCoinStatuses(List<Cryptocurrency> currenciesList) {
		for (Cryptocurrency currency : currenciesList) {
			String currencyUrl = String.format(STATUS_UPDATE_URL, currency.getId());
			String statusUpdatesJSON = client.getJSON(currencyUrl);
			try {
				/*
				 * Object Mapper to return a hashmap with a list of status updates for each
				 * currency
				 */
				map = objectMapper.readValue(statusUpdatesJSON,
						new TypeReference<HashMap<String, List<StatusUpdate>>>() {
						});
				// Get map with key
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
}
