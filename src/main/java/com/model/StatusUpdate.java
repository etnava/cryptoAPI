package com.model;

public class StatusUpdate {

	String description = "";
	String user_title = "";

	public StatusUpdate(String description, String user_title) {
		super();
		this.description = description;
		this.user_title = user_title;
	}

	public StatusUpdate() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUser_title() {
		return user_title;
	}

	public void setUser_title(String user_title) {
		this.user_title = user_title;
	}

}
