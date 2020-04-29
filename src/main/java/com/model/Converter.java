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
	List<Cryptocurrency> listCrypto;
	Map<String, List<StatusUpdate>> map;
	List<StatusUpdate> statusUpdates;

	public Converter() {
	}
	
	public Converter(int numberOfCoins) {
		this.numberOfCoins = numberOfCoins;
		/*
		 * Temp URL Because market is broken
		 */
		this.url = "https://api.coingecko.com/api/v3/coins/markets?vs_currency=aud&ids=bitcoin%2C%20ethereum%2C%20vechain%2C%20litecoin%2Cstellar&order=market_cap_desc&per_page=10&page=1&sparkline=false";
//		this.url = String.format(
//				"https://api.coingecko.com/api/v3/coins/markets?vs_currency=aud&order=market_cap_desc&per_page=%d&page=1&sparkline=false",
//				numberOfCoins);
	}



	/*
	 * Does the JSON to Java conversion
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

	
	/*
	 * Used to convert it as a JSON String
	 */
	public String convertJavaToJsonString(List<Cryptocurrency> list) {
		String json = null;
		try {
			json = objectMapper.writeValueAsString(list);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}
	
	/*
	 * Converting the JSON String as a JSON object
	 */
	public JSONObject convertToJsonObject(String json) {
		JSONObject jsonObject = new JSONObject(json);
		return jsonObject;
		
	}

	public int getNumberOfCoins() {
		return numberOfCoins;
	}

	public void setNumberOfCoins(int numberOfCoins) {
		this.numberOfCoins = numberOfCoins;
	}
	
}
