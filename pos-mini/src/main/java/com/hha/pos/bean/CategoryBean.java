package com.hha.pos.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import com.hha.pos.entity.Category;
import com.hha.pos.services.CategoryService;

@ApplicationScoped
public class CategoryBean {

	@Named
	@Produces
	private List<Category> categories;
	
	@Inject
	private CategoryService service;

	@PostConstruct
	public void init() {
		categories = service.getAll();
	}
	
}
