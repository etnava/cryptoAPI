package client;

import org.springframework.web.client.RestTemplate;

/*
 * Handles API Calling, gets the JSON Data
 */

public class ApiClient2 {

	private RestTemplate restTemplate = new RestTemplate();

	public ApiClient2() {
	}
	
	/*
	 * Converts the API url as a String
	 */
	public String getJSON(String url) {
		return restTemplate.getForObject(url, String.class);
	}

}
