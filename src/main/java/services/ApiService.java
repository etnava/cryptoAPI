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
	 * Converts the API url as a String
	 */
	public String getJSON(String url) {
		return restTemplate.getForObject(url, String.class);
	}

}
