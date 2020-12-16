package com.hha.pos.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.annotation.ManagedProperty;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.hha.pos.entity.Product;
import com.hha.pos.services.ProductService;

@Named
@ViewScoped
public class ProductBean implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	@ManagedProperty("#{param.category}")
	private int categoryId;
	
	@Inject
	private ProductService service;
	
	private List<Product> products;
	
	@PostConstruct
	public void init() {
		categoryId = categoryId == 0 ? 1 : categoryId;
		products = service.findByCategoryId(categoryId);
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}


}
