package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import client.CoinGeckoClient;
import model.Cryptocurrency;

/*
 * Handles JSON to Java Conversion
 */

public class CoinService {

	@Autowired
	CoinGeckoClient client;

	public CoinService() {
	}

	public List<Cryptocurrency> getAllCoins() {
		List<Cryptocurrency> currencies = client.getCoinGeckoCoinsAPI(10);
		for (Cryptocurrency c : currencies) {
			c.setStatusUpdates(client.getStatusUpdates(client.getCoinGeckoStatusUpdateMapAPI(c)));
		}
		return currencies;
	}

	public Cryptocurrency getCoin(String id) {
		Cryptocurrency c = client.getCoinGeckoCoinAPI(id);
		if (c == null) {
			return null;
		}
		c.setStatusUpdates(client.getStatusUpdates(client.getCoinGeckoStatusUpdateMapAPI(c)));
		return c;
	}
}
