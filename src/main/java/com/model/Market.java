package com.model;

public class Market {
	public double aud;
	public String aud_market_cap;

	public Market(double aud, String aud_market_cap) {
		super();
		this.aud = aud;
		this.aud_market_cap = aud_market_cap;
	}

	public Market() {
		super();
	}

	public double getAud() {
		return aud;
	}

	public void setAud(double aud) {
		this.aud = aud;
	}

	public String getAud_market_cap() {
		return aud_market_cap;
	}

	public void setAud_market_cap(String aud_market_cap) {
		this.aud_market_cap = aud_market_cap;
	}

}
