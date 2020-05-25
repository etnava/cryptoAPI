package application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

import client.CoinGeckoClient;
import services.CoinService;
import services.CoinService;

// Remove And move to root folder remove basepackages.
@ComponentScan(basePackages = { "controller" })
@SpringBootApplication
public class CryptocurrencyAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(CryptocurrencyAppApplication.class, args);
	}

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}


	@Bean
	public CoinService coinService() {
		return new CoinService();
	}

	@Bean
	public CoinGeckoClient client() {
		return new CoinGeckoClient();
	}
}
