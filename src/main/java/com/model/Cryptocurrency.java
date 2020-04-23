package com.model;

public class Cryptocurrency {

	private int id;
	private String price;
	private String marketCap;
	private StatusUpdate statusUpdate;

	public Cryptocurrency(int id, String price, String marketCap, StatusUpdate statusUpdate) {
		this.id = id;
		this.price = price;
		this.marketCap = marketCap;
		this.statusUpdate = statusUpdate;
	}

	public long getId() {
		return id;
	}

	public String getPrice() {
		return price;
	}

	public String getMarketCap() {
		return marketCap;
	}

	public StatusUpdate getStatusUpdate() {
		return statusUpdate;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public void setMarketCap(String marketCap) {
		this.marketCap = marketCap;
	}

	public void setStatusUpdate(StatusUpdate statusUpdate) {
		this.statusUpdate = statusUpdate;
	}

}
