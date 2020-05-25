
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

import client.CoinGeckoClient;
import services.CoinService;
import services.CoinService;

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
	public CoinService getCoinService() {
		return new CoinService();
	}

	@Bean
	public CoinGeckoClient getCoinGeckoClient() {
		return new CoinGeckoClient();
	}
}
