package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import client.ApiClient2;
import model.Cryptocurrency;

/*
 * Handles JSON to Java Conversion
 */

public class CoinService2 { // Renamed to coin service, call it the resouce that you are servicing.

	@Autowired
	ApiClient2 client;

	public CoinService2() {
		super();
	}

	public List<Cryptocurrency> getEverything() {
		client.setNumCoins(5);
		return null;
	}

	public Cryptocurrency getCryptocurrency(String id) {
		List<Cryptocurrency> list = getEverything();
		for (Cryptocurrency c : list) {
			if (c.getId().equals(id)) {
				return c;
			}
		}
		return null;
	}
}
