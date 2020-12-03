package com.hha.library.dto;

public class BookDTO {

	private String name;
	
	private String author;
	
	private String version;

	public BookDTO(String name, String author, String version) {
		super();
		this.name = name;
		this.author = author;
		this.version = version;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	
}
