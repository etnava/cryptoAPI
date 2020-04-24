package com.cryptocurrencyApp;

import java.io.File;
import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.Cryptocurrency;

@ComponentScan(basePackages = { "com.controller" })
@SpringBootApplication
public class CryptocurrencyAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(CryptocurrencyAppApplication.class, args);
		
		ObjectMapper objectMapper = new ObjectMapper();
		Cryptocurrency cryptocurrency = new Cryptocurrency("bitcoin");
		try {
			objectMapper.writeValue(new File("target/cryptocurrency.json"), cryptocurrency);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
