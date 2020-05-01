package com.model;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/*
 * Handles JSON to Java Conversion and Vice Versa
 * 
 */
public class Converter {

	ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	final String STATUS_UPDATES = "status_updates";
	private int numberOfCoins;
	private String url = "";
	private List<Cryptocurrency> listCrypto;
	Map<String, List<StatusUpdate>> map;
	List<StatusUpdate> statusUpdates;

	public Converter() {
	}

	public Converter(int numberOfCoins) {
		this.numberOfCoins = numberOfCoins;
		this.url = String.format(
				"https://api.coingecko.com/api/v3/coins/markets?vs_currency=aud&order=market_cap_desc&per_page=%d&page=1&sparkline=false",
				numberOfCoins);
		
		// NOT THE BEST WAY because what if you get new data, maybe theres a way to always be calling convertJsonToJava..?
		this.listCrypto = convertJsonToJava();
	}

	/*
	 * Does the JSON to Java conversinn
	 */
	public List<Cryptocurrency> convertJsonToJava() {
		try {
			listCrypto = objectMapper.readValue(new URL(url), new TypeReference<List<Cryptocurrency>>() {
			});
			for (Cryptocurrency coin : listCrypto) {
				map = objectMapper.readValue(new URL(
						String.format("https://api.coingecko.com/api/v3/coins/%s/status_updates", coin.getId())),
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

	public int getNumberOfCoins() {
		return numberOfCoins;
	}

	public void setNumberOfCoins(int numberOfCoins) {
		this.numberOfCoins = numberOfCoins;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<Cryptocurrency> getListCrypto() {
		return listCrypto;
	}

	public void setListCrypto(List<Cryptocurrency> listCrypto) {
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
