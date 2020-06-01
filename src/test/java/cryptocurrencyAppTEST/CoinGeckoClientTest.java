package cryptocurrencyAppTEST;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.junit.WireMockRule;

import client.CoinGeckoClient;
import model.Cryptocurrency;
import model.StatusUpdate;

public class CoinGeckoClientTest {

	private final String STATUS_UPDATE_KEY = "status_updates";
	private final String SINGLE_COIN_URL = "https://api.coingecko.com/api/v3/coins/markets?vs_currency=aud&ids=%s&order=market_cap_desc&per_page=100&page=1&sparkline=false";
	private final String MULTIPLE_COINS_URL = "https://api.coingecko.com/api/v3/coins/markets?vs_currency=aud&order=market_cap_desc&per_page=%d&page=1&sparkline=false";
	private final String STATUS_UPDATE_URL = "https://api.coingecko.com/api/v3/coins/%s/status_updates";

	@Mock
	RestTemplate mockRestTemplate;

	@Mock
	ObjectMapper mockObjectMapper;

	@Mock
	Map<String, List<StatusUpdate>> mockMap;

	@Mock
	Cryptocurrency mockCoin;

	@Mock
	List<Cryptocurrency> mockList;

	@InjectMocks
	CoinGeckoClient client = new CoinGeckoClient();

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void that_getJSON_works() {
		String json = "mockJson";
		String url = "url";
		when(mockRestTemplate.getForObject(url, String.class)).thenReturn(json);
		assertEquals(json, client.getJSON(url));
	}

	@Test
	public void test_getStatusUpdates_works() {
		List<StatusUpdate> fakeList = new ArrayList<>();
		when(mockMap.get(STATUS_UPDATE_KEY)).thenReturn(fakeList);
		List<StatusUpdate> list = client.getStatusUpdates(mockMap);
		assertEquals(list, client.getStatusUpdates(mockMap));
	}

	@Test
	public void test_get_coin_gecko_Coin_API_for_valid_coin() throws JsonMappingException, JsonProcessingException {
		
		String id = "bitcoin";
		
		String coinURL = "coinURL";
		String coinJSON = "mockJson";
		when(mockRestTemplate.getForObject(coinURL, String.class)).thenReturn(coinJSON);
		
		List<Cryptocurrency> list = new ArrayList<>();
		List<StatusUpdate> updates = new ArrayList<>();
		Cryptocurrency bitcoin = new Cryptocurrency("testCoin", 0 ,"market-cap", updates);
		list.add(bitcoin);
		
		when(mockObjectMapper.readValue(coinJSON, new TypeReference<List<Cryptocurrency>>() {
		})).thenReturn(list);
		
		Cryptocurrency c = client.getCoinGeckoCoinAPI(id);

	}

//	@Test
//	public void test_get_coinGecko_map_api() throws JsonMappingException, JsonProcessingException {
//		Cryptocurrency c = new Cryptocurrency();
//		String currencyURL = "currencyURL";
//		String statusUpdateJSON = "updateJSON";
//		when(client.getJSON(currencyURL)).thenReturn(statusUpdateJSON);
//		HashMap<String, List<StatusUpdate>> map = new HashMap<>();
//		when(mockObjectMapper.readValue(statusUpdateJSON, new TypeReference<HashMap<String, List<StatusUpdate>>>() {
//		})).thenReturn(map);
//		HashMap<String, List<StatusUpdate>> mapper = client.getCoinGeckoStatusUpdateMapAPI(mockCoin);
//
//	}

	/*
	 * TODO It is not mocking within the try catch block what do I need to do?
	 * 
	 */

//	@Test
//	public void test_all_coins_api() throws JsonMappingException, JsonProcessingException {
//		int numCoins = 5;
//		String coinsUrl = "coinsUrl";
//		String allCoinsJSON = "allCoinsJson";
//		when(client.getJSON(coinsUrl)).thenReturn(allCoinsJSON);
//		List<Cryptocurrency> list = new ArrayList<>();
//		when(mockObjectMapper.readValue(allCoinsJSON, new TypeReference<List<Cryptocurrency>>() {
//		})).thenReturn(list);
//		List<Cryptocurrency> otherList = client.getCoinGeckoCoinsAPI(numCoins);
//	}
}
