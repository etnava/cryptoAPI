package cryptocurrencyAppTEST;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import client.CoinGeckoClient;
import model.Cryptocurrency;
import model.StatusUpdate;

public class CoinGeckoClientTest {

	private final String STATUS_UPDATE_KEY = "status_updates";
	private final String SINGLE_COIN_URL = "https://api.coingecko.com/api/v3/coins/markets?vs_currency=aud&ids=%s&order=market_cap_desc&per_page=100&page=1&sparkline=false";

	@Mock
	RestTemplate mockRestTemplate;

	@Mock
	ObjectMapper mockObjectMapper;

	@Mock
	Map<String, List<StatusUpdate>> mockMap;

	@Mock
	Cryptocurrency mockCoin;

	@Mock
	ArrayList<Cryptocurrency> mockList;

	@Mock
	TypeReference<List<Cryptocurrency>> mockType;

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
		String coinURL = String.format(SINGLE_COIN_URL, id);
		String coinJSON = "mockJson";
		TypeReference<ArrayList<Cryptocurrency>> type = new TypeReference<ArrayList<Cryptocurrency>>() {
		};
		when(client.getJSON(coinURL)).thenReturn(coinJSON);
		List<StatusUpdate> updates = new ArrayList<>();
		Cryptocurrency c = new Cryptocurrency("testCoin", 0, "market-cap", updates);
		when(mockObjectMapper.readValue(eq(coinJSON), eq(type))).thenReturn(mockList);
		client.getCoinGeckoCoin(id);

	}

	@Test
	public void test_get_coinGecko_map_api() throws JsonMappingException, JsonProcessingException {
		Cryptocurrency c = new Cryptocurrency();
		String currencyURL = "currencyURL";
		String statusUpdateJSON = "updateJSON";
		when(client.getJSON(currencyURL)).thenReturn(statusUpdateJSON);
		HashMap<String, List<StatusUpdate>> map = new HashMap<>();
		when(mockObjectMapper.readValue(statusUpdateJSON, new TypeReference<HashMap<String, List<StatusUpdate>>>() {
		})).thenReturn(map);
		HashMap<String, List<StatusUpdate>> mapper = client.getCoinGeckoStatusUpdateMapAPI(mockCoin);

	}

	@Test
	public void test_all_coins_api() throws JsonMappingException, JsonProcessingException {
		int numCoins = 5;
		String coinsUrl = "coinsUrl";
		String allCoinsJSON = "allCoinsJson";
		when(client.getJSON(coinsUrl)).thenReturn(allCoinsJSON);
		List<Cryptocurrency> list = new ArrayList<>();
		when(mockObjectMapper.readValue(allCoinsJSON, new TypeReference<List<Cryptocurrency>>() {
		})).thenReturn(list);
		List<Cryptocurrency> otherList = client.getCoinGeckoCoinsAPI(numCoins);
	}

}
