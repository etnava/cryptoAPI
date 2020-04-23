package com.model;

public class StatusUpdate {
	private String title;
	private String description;
	private String createdAt;

	public StatusUpdate(String title, String description, String createdAt) {
		super();
		this.title = title;
		this.description = description;
		this.createdAt = createdAt;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

}
