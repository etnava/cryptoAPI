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

@ComponentScan(basePackages = { "com.controller" })
@SpringBootApplication
public class CryptocurrencyAppApplication {

	public static void main(String[] args) {
		//SpringApplication.run(CryptocurrencyAppApplication.class, args);

		
		// Doing everything in main then will convert later.
		ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		List<Cryptocurrency> listCrypto;
		try {
			listCrypto = objectMapper.readValue(new URL("https://api.coingecko.com/api/v3/coins/markets?vs_currency=aud&order=market_cap_desc&per_page=100&page=1&sparkline=false"), new TypeReference<List<Cryptocurrency>>() {});
			for (Cryptocurrency c : listCrypto) {
				System.out.println(c.getId());
				System.out.println("AUD: " + c.getCurrent_price());
				System.out.println("AUD: " + c.getMarket_cap());
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
	}
}
