package services;

import org.springframework.web.client.RestTemplate;

/*
 * Handles API Calling, gets the JSON Data
 */

public class ApiService {

	private RestTemplate restTemplate = new RestTemplate();

	public ApiService() {
	}

	
	/*
	 * Gets the whole object then parses back as a string.
	 */
	public String getJSON(String url) {
		return restTemplate.getForObject(url, String.class);
	}

}
