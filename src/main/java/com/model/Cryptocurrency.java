package com.model;

public class Cryptocurrency {

	private String id;
	private double current_price;
	private String market_cap;

	public Cryptocurrency() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Cryptocurrency(String id, double current_price, String market_cap) {
		super();
		this.id = id;
		this.current_price = current_price;
		this.market_cap = market_cap;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getCurrent_price() {
		return current_price;
	}

	public void setCurrent_price(double current_price) {
		this.current_price = current_price;
	}

	public String getMarket_cap() {
		return market_cap;
	}

	public void setMarket_cap(String market_cap) {
		this.market_cap = market_cap;
	}

}
