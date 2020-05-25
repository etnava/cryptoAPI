package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import client.CoinGeckoClient;
import model.Cryptocurrency;

/*
 * Handles JSON to Java Conversion
 */

public class CoinService { // Renamed to coin service, call it the resouce that you are servicing.

	@Autowired
	CoinGeckoClient client;

	// Num coins is set in Bean on application
	
	
	public CoinService() {
	}

	public List<Cryptocurrency> getCurrenciesList() {
		// Set how many coins to search for
		return client.getCoins(10);
	}

	public Cryptocurrency getCoin(String id) {
		return client.getCoin(id);
	}
}
