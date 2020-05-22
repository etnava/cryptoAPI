package services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import client.ApiClient;
import model.Cryptocurrency;
import model.StatusUpdate;

/*
 * Handles JSON to Java Conversion
 */

public class CoinService2 { // Renamed to coin service, call it the resouce that you are servicing.

	private final String STATUS_UPDATES = "status_updates";
	private final String COIN_MARKET_URL = "https://api.coingecko.com/api/v3/coins/markets?vs_currency=aud&order=market_cap_desc&per_page=%d&page=1&sparkline=false"; // Move to Client
	private final String STATUS_UPDATE_URL = "https://api.coingecko.com/api/v3/coins/%s/status_updates"; // Move to Client
	private ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
			false); // Move to Client
	private String url;
	private ApiClient apiService = new ApiClient(); // Rename it to client, need one for each call. Handles with Services handles communication. // 21->26
	private List<Cryptocurrency> currenciesList; // Removed 
	private Map<String, List<StatusUpdate>> map = new HashMap<String, List<StatusUpdate>>(); // getAll, coin.geckoclient getStatusUpdates getCoins
// GetAll Function to call both API
	public CoinService2(int numberOfCoins) {
		setUrl(String.format(COIN_MARKET_URL, numberOfCoins)); // Moved to client, Use Autowired on client
		convertJsonToJava();
	}

	public void updateList() {
		convertJsonToJava();
	}
	
	public void getEverything() {
		int numOfCoins = 5;
		
	}

	private void convertJsonToJava() { // Get All Coins and Get ID
		String jsonCoins = apiService.getJSON(getUrl()); // do it client.getCoins pass coins here return, Client has to return Java File type
		try {
			// Initial Set Currencies list (No status Updates)
			setCurrenciesList(objectMapper.readValue(jsonCoins, new TypeReference<List<Cryptocurrency>>() {
			}));
			
			// Replace current currencies list with updated currencies list with status updates
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
			String statusUpdatesJSON = apiService.getJSON(currencyUrl);
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
