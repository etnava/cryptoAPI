package com.cryptocurrencyApp;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.model.Cryptocurrency;
import com.model.MapObjects;
import com.model.StatusUpdate;

@ComponentScan(basePackages = { "com.controller" })
@SpringBootApplication
public class CryptocurrencyAppApplication {

	public static void main(String[] args) {
		 SpringApplication.run(CryptocurrencyAppApplication.class, args);
		// Doing everything in main then will convert later.
//
//		int numberOfCoinsToSearch = 5;
//		MapObjects mapObjects = new MapObjects(numberOfCoinsToSearch);
//		List<Cryptocurrency> listCrypto;
//		listCrypto = mapObjects.convertJsonToJava();
//
//		for (Cryptocurrency c : listCrypto) {
//			System.out.println(c.getId());
//			if (c.getStatusUpdates().isEmpty()) {
//				System.out.println("Description: ");
//				System.out.println("Title: ");
//				System.out.println("Created at: ");
//			} else {
//				for (StatusUpdate statusUpdate : c.getStatusUpdates()) {
//					System.out.println("Description:" + statusUpdate.getDescription());
//					System.out.println("Title: " + statusUpdate.getUser_title());
//					System.out.println("Created at :" + statusUpdate.getCreated_at());
//				}
//			}
//		}
//		System.out.println(mapObjects.convertJavaToJson(listCrypto));

	}
}
