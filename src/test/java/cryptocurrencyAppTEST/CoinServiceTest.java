package cryptocurrencyAppTEST;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import client.CoinGeckoClient;
import model.Cryptocurrency;
import model.StatusUpdate;
import services.CoinService;

public class CoinServiceTest {



	@Mock
	CoinGeckoClient mockClient;
	
	@Mock
	Cryptocurrency mockCoin;
	
	@Mock
	StatusUpdate mockStatusUpdate;
	
	@InjectMocks
	CoinService coinService = new CoinService();

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void test_get_AllCoins() {
		List<Cryptocurrency> coins = new ArrayList<>();
		Cryptocurrency coin = new Cryptocurrency();
		coins.add(coin);
		when(mockClient.getCoinGeckoCoinsAPI(10)).thenReturn(coins);
		List<Cryptocurrency> list = coinService.getAllCoins();
		assertEquals(list,coins);
	}
	
	@Test
	public void test_getCoin() {
		Cryptocurrency coin = new Cryptocurrency();
		when(mockClient.getCoinGeckoCoinAPI("id")).thenReturn(coin);
		Cryptocurrency testCoin = coinService.getCoin("id");
		assertEquals(testCoin, coin);
	}
	
	@Test
	public void test_get_null_coin() {
		when(mockClient.getCoinGeckoCoinAPI("id")).thenReturn(null);
		Cryptocurrency testCoin = coinService.getCoin("id");
		assertEquals(testCoin, null);
	}

}