package com.model;

public class Cryptocurrency {

	private String id;
	private String symbol;
	private String name;

	public Cryptocurrency() {
		super();
	}

	public Cryptocurrency(String id, String symbol, String name) {
		this.id = id;
		this.symbol = symbol;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
