package com.hha.library.beans;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.hha.library.entity.Author;
import com.hha.library.service.AuthorService;

@Named
@RequestScoped
public class AuthorEditBean {
	
	private Author author;
	
	@Inject
	private AuthorService service;
	
	@PostConstruct
	public void init() {
		author = new Author();
	}
	
	public String save() {
		service.save(author);
		return "authors?faces-redirect=true";
	}
	
	public Author getAuthor() {
		return author;
	}
	
	public void setAuthor(Author author) {
		this.author = author;
	}
}
