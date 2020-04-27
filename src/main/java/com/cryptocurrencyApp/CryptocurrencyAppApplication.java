package com.cryptocurrencyApp;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.Cryptocurrency;
import com.model.Market;
import com.model.StatusUpdate;

@ComponentScan(basePackages = { "com.controller" })
@SpringBootApplication
public class CryptocurrencyAppApplication {

	public static void main(String[] args) {
		// SpringApplication.run(CryptocurrencyAppApplication.class, args);

		// Doing everything in main then will convert later.
		ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
				false);
		List<Cryptocurrency> listCrypto;
		Map<String, List<StatusUpdate>> map;
		List<StatusUpdate> statusUpdates;
		final String STATUS_UPDATES = "status_updates";
		String url = "https://api.coingecko.com/api/v3/coins/markets?vs_currency=aud&order=market_cap_desc&per_page=50&page=1&sparkline=false";
		
		try {
			listCrypto = objectMapper.readValue(new URL(url), new TypeReference<List<Cryptocurrency>>() {
			});

			for (Cryptocurrency c : listCrypto) {
				map = objectMapper.readValue(new URL(String.format("https://api.coingecko.com/api/v3/coins/%s/status_updates" , c.getId())), 
						new TypeReference<Map<String, List<StatusUpdate>>>() {});
				statusUpdates = map.get(STATUS_UPDATES);
				System.out.println(c.getId());
				for (StatusUpdate statusUpdate : statusUpdates) {
					System.out.println(statusUpdate.getDescription());
					System.out.println(statusUpdate.getUser_title());
				}
			}
			
			
			
//			for (Cryptocurrency c : listCrypto) {
////				StatusUpdate statusUpdate = objectMapper.readValue(new URL(String.format("https://api.coingecko.com/api/v3/coins/%s/status_updates" , c.getId())), 
////						new TypeReference<StatusUpdate>() {});
//				System.out.println(c.getId());
//				System.out.println("Aud: " + c.getCurrent_price());
//				System.out.println("aud_market_cap:" + c.getMarket_cap());
//				
//			}

			/*
			 * ** URL TOO LONG... not using this. Add String builder for each id
			 * 
			 * for (int i = 0; i < listCrypto.size() - 1; i ++) { if (i == 0) builder =
			 * listCrypto.get(i).getId(); else { builder = builder + "%2C" +
			 * listCrypto.get(i).getId(); } } builder = builder.replace(" ", "%20");
			 * System.out.println(builder);
			 * 
			 * String url = urlStart + builder + urlEnd; map = objectMapper.readValue(new
			 * URL(url), new TypeReference<Map<String,Market>>(){});
			 * 
			 */

			// ** Too slow, not using, will use top 50 bitcoin on market"

//			for (Cryptocurrency c : listCrypto) {
//				urlMiddle = c.getId();
//				
//				if (urlMiddle.contains(" ")) {
//					urlMiddle = urlMiddle.replace(" ", "%20");
//				}
//				String url = urlStart + urlMiddle + urlEnd;
//				map = objectMapper.readValue(new URL(url), new TypeReference<Map<String, Market>>() {
//				});
//				
//				Market market = map.get(c.getId());
//				c.setCurrent_price(market.getAud());
//				c.setMarket_cap(market.getAud_market_cap());
//				System.out.println(c.getId());
//				System.out.println("aud: " + c.getCurrent_price());
//				System.out.println("aud_market_cap: " + c.getMarket_cap());
//				continue;
//			}
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
	}
}
