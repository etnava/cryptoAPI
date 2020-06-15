package cryptocurrencyAppTEST;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.Cryptocurrency;

import static org.junit.Assert.*;

public class StepDefinitions {

	private String serviceName;
	private int statusCode;

	@Given("I want to execute service {string}")
	public void is_ServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	@When("I submit the GET REQUEST")
	public void dothis() {

	}

	@Then("I verify that the {int}")
	public void is_statusCode(int statusCode) {
		this.statusCode = statusCode;
		System.out.println("hey");
	}

}
