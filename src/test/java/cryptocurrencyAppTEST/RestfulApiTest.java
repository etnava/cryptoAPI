package cryptocurrencyAppTEST;

import org.junit.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class RestfulApiTest {

	@BeforeClass
	public void init() {
		baseURI = "http://localhost:1111/api";
	}

	@Test
	public void that_base_request_validate_statusCode_returnsOK() {
		given().get("allcoins").then().statusCode(200);
	}

	@Test
	public void that_bitcoin_request_validate_statusCode_returnOK() {
		given().get("bitcoin").then().statusCode(200);
	}

	@Test
	public void handle_incorrect_status_code() {
		given().get("invalid").then().statusCode(400);
	}

	@Test
	public void that_bitcoin_id_return_bitcoin() {
		given().get("bitcoin").then().body("id", equalTo("bitcoin"));
	}

	@Test
	public void that_validate_header_is_application_json() {
		given().get("allcoins").then().contentType("application/json");
	}

	@Test
	public void that_bitcoin__validate_header_is_application_json() {
		given().get("bitcoin").then().contentType("application/json");
	}

	@Test
	public void that_response_time_is_OK() {
		given().get("allcoins").then().time(lessThan(1000L));
	}

	@Test
	public void that_bitcoin_response_time_is_OK() {
		given().get("bitcoin").then().time(lessThan(1000L));
	}

//	@Test
//	public void that_bitcoin_market_cap_is_correct() {
//		given().get("bitcoin").then().body("market_cap", equalTo("248943817915"));
//	}
//	
//	@Test
//	public void that_bitcoin_market_price_is_correct() {
//		given().get("bitcoin").then().body("current_price", equalTo(13558.62f));
//	}

	@Test
	public void that_response_is_JSON_array_of_10() {
		given().get("allcoins").then().body("size()", is(10));
	}

	@Test
	public void that_get_bitcoin_is_JSON_array_size_of_1() {
		given().get("bitcoin").then().body("size()", is(4));
	}

}
