package com.model;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/*
 * Handles JSON to Java Conversion and Vice Versa
 * 
 */

public class Converter {

	private ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
			false);
	private final String STATUS_UPDATES = "status_updates";
	private String url;
	private final String COIN_MARKET_URL = "https://api.coingecko.com/api/v3/coins/markets?vs_currency=aud&order=market_cap_desc&per_page=%d&page=1&sparkline=false";
	private final String STATUS_UPDATE_URL = "https://api.coingecko.com/api/v3/coins/%s/status_updates";
	private List<Cryptocurrency> listCrypto;
	private Map<String, List<StatusUpdate>> map;
	private List<StatusUpdate> statusUpdates;

	public Converter(int numberOfCoins) {
		this.url = String.format(COIN_MARKET_URL, numberOfCoins);
		this.listCrypto = convertJsonToJava();
	}

	private List<Cryptocurrency> convertJsonToJava() {
		try {
			listCrypto = objectMapper.readValue(new URL(url), new TypeReference<List<Cryptocurrency>>() {
			});
			for (Cryptocurrency coin : listCrypto) {
				map = objectMapper.readValue(new URL(String.format(STATUS_UPDATE_URL, coin.getId())),
						new TypeReference<Map<String, List<StatusUpdate>>>() {
						});
				statusUpdates = map.get(STATUS_UPDATES);
				coin.setStatusUpdates(statusUpdates);
			}
			setListCrypto(listCrypto);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listCrypto;
	}

	public List<Cryptocurrency> getListCrypto() {
		return listCrypto;
	}

	private void setListCrypto(List<Cryptocurrency> listCrypto) {
		this.listCrypto = listCrypto;
	}

	public Cryptocurrency getCryptocurrency(String id) {
		List<Cryptocurrency> list = getListCrypto();
		for (Cryptocurrency c : list) {
			if (c.getId().equals(id)) {
				return c;
			}
		}
		return null;
	}

}
