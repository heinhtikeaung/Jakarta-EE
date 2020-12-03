package com.hha.library.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.hha.library.entity.Author;
import com.hha.library.service.AuthorService;


@Named
@RequestScoped
public class AuthorBean {
	
	private List<Author> authors;
	
	@Inject
	private AuthorService service;

	@PostConstruct
	public void init() {
		authors = service.getAll();
	}
	
	public List<Author> getAuthors() {
		return authors;
	}
	

}
