package com.controller;


import io.restassured.module.mockmvc.RestAssuredMockMvc;

import org.junit.*;

class RestControllerTest {

	@AfterClass
	static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void initialiseRestAssuredMockMVCStandalone() {
		RestAssuredMockMvc.standaloneSetup(new CryptocurrencyRestController());
	}
	
	
	
}
