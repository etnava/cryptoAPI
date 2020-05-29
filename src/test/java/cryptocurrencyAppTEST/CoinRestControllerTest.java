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

import controller.CoinRestController;
import model.Cryptocurrency;
import services.CoinService;

public class CoinRestControllerTest {

	@Mock
	CoinService coinService;

	@Mock
	HttpServletResponse mockResponse;

	@InjectMocks
	CoinRestController controller = new CoinRestController();

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void test_getAllCoins() {
		List<Cryptocurrency> coins = new ArrayList<>();
		when(coinService.getAllCoins()).thenReturn(coins);
		List<Cryptocurrency> list = controller.getAllCoins();
		assertEquals(list, coins);
	}

	@Test
	public void test_get_real_coin_works() {
		Cryptocurrency coin = new Cryptocurrency();
		when(coinService.getCoin("id")).thenReturn(coin);
		Cryptocurrency testCoin = controller.getCoin("id", mockResponse);
		assertEquals(testCoin, coin);
	}
	
	@Test
	public void test_get_null_coin() {
		when(coinService.getCoin("id")).thenReturn(null);
		Cryptocurrency testCoin = controller.getCoin("id", mockResponse);
		verify(mockResponse).setStatus(404);
		assertEquals(testCoin, null);
	}

}
