package cryptocurrencyAppTEST;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Before;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.junit.Rule;
import org.junit.Test;

import com.github.tomakehurst.wiremock.junit.WireMockRule;

public class WiremockTest {

	private int port = 8089;
	private String wiremockServer = "http://localhost:" + port + "/api";
	private String mockCoinBody = "[{\"id\":\"bitcoin\",\"current_price\":13781.59,\"market_cap\":\"253284625049\",\"statusUpdates\":[]},{\"id\":\"ethereum\",\"current_price\":295.44,\"market_cap\":\"32770242975\",\"statusUpdates\":[]},{\"id\":\"ripple\",\"current_price\":0.306718,\"market_cap\":\"13551860317\",\"statusUpdates\":[]},{\"id\":\"tether\",\"current_price\":1.54,\"market_cap\":\"13327562237\",\"statusUpdates\":[]},{\"id\":\"bitcoin-cash\",\"current_price\":363.37,\"market_cap\":\"6691818244\",\"statusUpdates\":[]},{\"id\":\"bitcoin-cash-sv\",\"current_price\":290.85,\"market_cap\":\"5357699503\",\"statusUpdates\":[]},{\"id\":\"litecoin\",\"current_price\":65.65,\"market_cap\":\"4249649942\",\"statusUpdates\":[{\"description\":\"Litecoin Foundation Partners With MeconCash, Enabling Fiat Withdrawal At Over 13,000 ATMs Across South Korea.\\r\\n\\r\\n\\uD83D\\uDC47 Read More\\r\\nhttps://litecoin-foundation.org/litecoin-foundation-partners-with-meconcash-enabling-fiat-withdrawal-at-over-13000-atms-across-south-korea/\",\"user_title\":\"Operations Director\",\"created_at\":\"2020-03-02T13:08:21.379Z\"},{\"description\":\"Litecoin Foundation partners with Cred, Litecoin holders can start earning up to 10% interest on their digital assets! Sign up to start earning now: mycred.io/litecoin\\r\\n\\r\\nRead more about it here:\\r\\nlitecoin-foundation.org/13731-2/\\r\\n\\r\\n\",\"user_title\":\"Operations Director\",\"created_at\":\"2020-02-14T02:31:28.789Z\"},{\"description\":\"Litecoin meetup and pre-Blockshow networking party in Singapore \\r\\n\\r\\nDate: & Time: 13 Nov 2019 - 6pm till late.\\r\\nLocation:  MEDZS Clifford Centre Raffles Place #01-01\\r\\n\\r\\nRSVP now – bit.ly/ecxxparty\\r\\n\\r\\nCo-organised by Xfers, Ledger, Ecxx.com, Litecoin foundation\",\"user_title\":\"Operations Director\",\"created_at\":\"2019-11-08T05:03:54.985Z\"},{\"description\":\"Litecoin Summit 2019 was successfully held in Las Vegas.\\r\\n\\r\\nRecorded live stream here: \\r\\nDay 1: https://www.youtube.com/watch?v=7YAGPK4vMrQ\\r\\nDay 2: https://www.youtube.com/watch?v=_rhgu_WsEus&t=30424s\",\"user_title\":\"Operations Director\",\"created_at\":\"2019-11-08T04:56:46.406Z\"},{\"description\":\"Introducing Litewallet: A New & Improved Version Of LoafWallet\\r\\n\\r\\n\\uD83D\\uDC47 Read More\\r\\nhttps://litecoin-foundation.org/introducing-litewallet-a-new-improved-version-of-loafwallet/\",\"user_title\":\"Operations Director\",\"created_at\":\"2019-11-08T04:52:00.205Z\"},{\"description\":\"The Litecoin MimbleWimble Extension Block Proposal has been Published.\\r\\n\\r\\n\\uD83D\\uDC47 Read More:\\r\\nhttps://litecoin.com/en/news/the-litecoin-mimblewimble-extension-block-proposal-has-been-published\",\"user_title\":\"Operations Director\",\"created_at\":\"2019-11-08T04:41:14.435Z\"},{\"description\":\"The Litecoin Foundation Joins UNICEF Kid Power.\\r\\n\\r\\n\\uD83D\\uDC47 Read More: \\r\\nhttps://medium.com/@LitecoinDotCom/the-litecoin-foundation-joins-unicef-kid-power-5d0cf06e98bf\",\"user_title\":\"Operations Director\",\"created_at\":\"2019-09-03T07:46:40.351Z\"},{\"description\":\"The #PayWithLitecoin Movement Gets A Boost From SPEDN\\r\\n\\r\\nhttps://litecoin-foundation.org/the-paywithlitecoin-movement-gets-a-boost-from-spedn/\",\"user_title\":\"Operations Director\",\"created_at\":\"2019-09-03T07:44:17.189Z\"},{\"description\":\"Litecoin Named Official Cryptocurrency of the Miami Dolphins\\r\\n\\r\\n\\uD83D\\uDC47 Read More:\\r\\nhttps://www.miamidolphins.com/news/litecoin-named-official-cryptocurrency-of-the-miami-dolphins\\r\\n\\r\\nhttps://litecoin-foundation.org/litecoin-named-official-cryptocurrency-of-the-miami-dolphins/\\r\\n\",\"user_title\":\"Operations Director\",\"created_at\":\"2019-07-17T03:29:20.628Z\"},{\"description\":\"Litecoin is live on Flexa.  The Flexa network now supports LTC for payment at at more than 39,250 stores (and counting) across the United States. \\r\\n\\r\\n\\uD83D\\uDC47 Read More: \\r\\nhttps://medium.com/flexa/litecoin-is-live-on-flexa-4b6c38ae37d5\",\"user_title\":\"Operations Director\",\"created_at\":\"2019-07-04T13:47:51.800Z\"},{\"description\":\"The Litecoin Foundation Teams Up With Bibox And Ternio On Special Edition Litecoin BlockCard\\r\\n\\r\\n\\uD83D\\uDC47 Read More:\\r\\nlitecoin-foundation.org/the-litecoin-foundation-teams-up-with-bibox-and-ternio-on-special-edition-litecoin-blockcard/\",\"user_title\":\"Operations Director\",\"created_at\":\"2019-06-25T15:40:43.822Z\"},{\"description\":\"Franklyn Richards, Director of Litecoin Foundation, will be speaking at the World Blockchain Forum on 22 & 23 June 2019 at Marina Bay Sands, Singapore http://wbf.io/ \",\"user_title\":\"Operations Director\",\"created_at\":\"2019-06-18T03:04:24.731Z\"},{\"description\":\"Charlie Lee, creator of Litecoin, will be speaking at Asia Blockchain Summit on 2 & 3 July 2019 at Taipei Marriott.   Use code CHARLIE.L@ABS for discounted tickets http://www.abasummit.io.   \\r\\n\\r\\nLitecoin Foundation (https://litecoin-foundation.org) has a booth in the Summit.  Meet us there. \",\"user_title\":\"Operations Director\",\"created_at\":\"2019-06-14T03:21:14.399Z\"},{\"description\":\"Litecoin Summit 2019 is coming to Las Vegas on October 28th and 29th.\\r\\n\\r\\nFor more information, visit https://litecoinsummit.org/\",\"user_title\":\"Operations Director\",\"created_at\":\"2019-06-10T04:18:14.808Z\"},{\"description\":\"Litecoin Hashrate reaches another all time high with new L5 ASIC miners rumoured.\\r\\n\\r\\nRead more:\\r\\nlitecoin.com/en/news/litecoin-hashrate-reaches-another-all-time-high-with-new-l5-asic-miners-rumoured\",\"user_title\":\"Operations Director\",\"created_at\":\"2019-06-06T13:13:00.225Z\"},{\"description\":\"Travala.com Partners with Litecoin Foundation to Champion Crypto Payments. \\r\\n#TravelWithLitecoin www.travala.com/litecoin\\r\\n\\r\\nRead the full announcement here: bit.ly/2LumY3b\",\"user_title\":\"Operations Director\",\"created_at\":\"2019-05-14T13:56:43.282Z\"},{\"description\":\"Litecoin Foundation publishes “Understanding Litecoin: The Digital Currency for Payments” on Amazon.   \\r\\n\\r\\nhttps://www.amazon.com/Understanding-Litecoin-Digital-Currency-Payments-ebook/dp/B07QTKDM7Z/ref=sr_1_2?keywords=understanding+litecoin&qid=1556047514&s=books&sr=1-2\\r\\n\\r\\nhttps://litecoin-foundation.org\",\"user_title\":\"Operations Director\",\"created_at\":\"2019-04-24T09:24:51.732Z\"},{\"description\":\"Litecoin hashrate reaches all time high at 359.43TH/s on 4th April 2019, and reaches another new all time high at 371.92TH/s on 15th April 2019.    \\r\\n\\r\\nhttps://litecoin-foundation.org\",\"user_title\":\"Operations Director\",\"created_at\":\"2019-04-20T14:31:04.821Z\"}]},{\"id\":\"binancecoin\",\"current_price\":24.86,\"market_cap\":\"3677310681\",\"statusUpdates\":[]},{\"id\":\"eos\",\"current_price\":3.8,\"market_cap\":\"3559964772\",\"statusUpdates\":[]},{\"id\":\"tezos\",\"current_price\":3.93,\"market_cap\":\"2791160963\",\"statusUpdates\":[]}]";
	private String mockBitcoinBody = "{\"id\":\"bitcoin\",\"current_price\":13781.59,\"market_cap\":\"253284625049\",\"statusUpdates\":[]}";

	@Rule
	public WireMockRule wireMockRule = new WireMockRule(port);

	@Before
	public void init() {
		baseURI = wiremockServer;
	}

	/*
	 * Method Classes
	 */
	private void configureStubBitcoin() {
		stubFor(get(urlPathMatching("/.*")).willReturn(
				aResponse().withStatus(200).withHeader("Content-Type", "application/json").withBody(mockBitcoinBody)));
	}

	private void configureStubAll() {
		stubFor(get(urlPathMatching("/.*")).willReturn(
				aResponse().withStatus(200).withHeader("Content-Type", "application/json").withBody(mockCoinBody)));
	}

	private void configureStubNotFound() {
		stubFor(get(urlPathMatching("/.*")).willReturn(aResponse().withStatus(404)));
	}

	
	@Test
	public void testAllCoins() {

//		1. Stub endpoint
		configureStubAll();
//		2. Make a Request
		RestTemplate restTemplate = new RestTemplate();
		String resourceUrl = wiremockServer;
		ResponseEntity<String> response = restTemplate.getForEntity(resourceUrl + "/allcoins", String.class);
//		3. Verify
		assertNotNull(response);
		/*
		 * response.getStatusCodeValue(); For statuscode Value
		 */
		assertTrue("Status code not equal to 200", response.getStatusCode().equals(HttpStatus.OK));
		assertTrue("Contains fail", response.getBody().contains("\"id\":\"bitcoin\""));
	}

	@Test
	public void testBitcoin() {

		configureStubBitcoin();
		RestTemplate restTemplate = new RestTemplate();
		String resourceUrl = wiremockServer;
		ResponseEntity<String> response = restTemplate.getForEntity(resourceUrl + "/bitcoin", String.class);
		assertNotNull(response);
		assertTrue("Status code not equal to 200", response.getStatusCode().equals(HttpStatus.OK));
		assertTrue("Contains fail", response.getBody().contains("\"statusUpdates\":[]"));
	}

	@Test
	public void all_coins_return_statusCode_okay() {
		configureStubAll();
		given().get("allcoins").then().statusCode(200);

	}

	@Test
	public void bitcoin_return_statusCode_okay() {
		configureStubBitcoin();
		given().get("bitcoin").then().statusCode(200);
	}

	@Test
	public void handle_incorrect_status_code() {
		configureStubNotFound();
		given().get("invalid").then().statusCode(404);
	}

	@Test
	public void that_bitcoin_id_return_bitcoin() {
		configureStubBitcoin();
		given().get("bitcoin").then().body("id", equalTo("bitcoin"));
	}

	@Test
	public void that_validate_header_is_application_json() {
		configureStubAll();
		given().get("allcoins").then().contentType("application/json");
	}

	@Test
	public void that_bitcoin__validate_header_is_application_json() {
		configureStubBitcoin();
		given().get("bitcoin").then().contentType("application/json");
	}

	@Test
	public void that_response_time_is_OK() {
		configureStubAll();
		given().get("allcoins").then().time(lessThan(1000L));
	}

	@Test
	public void that_bitcoin_response_time_is_OK() {
		configureStubBitcoin();
		given().get("bitcoin").then().time(lessThan(1000L));
	}

	@Test
	public void that_response_is_JSON_array_of_10() {
		configureStubAll();
		given().get("allcoins").then().body("size()", is(10));
	}

	@Test
	public void that_get_bitcoin_is_JSON_array_size_of_1() {
		configureStubBitcoin();
		given().get("bitcoin").then().body("size()", is(4));
	}

	@Test
	public void that_bitcoin_market_cap_is_correct() {
		configureStubBitcoin();
		given().get("bitcoin").then().body("market_cap", equalTo("253284625049"));
	}

	@Test
	public void that_bitcoin_market_price_is_correct() {
		configureStubBitcoin();
		given().get("bitcoin").then().body("current_price", equalTo(13781.59f));
	}

}
