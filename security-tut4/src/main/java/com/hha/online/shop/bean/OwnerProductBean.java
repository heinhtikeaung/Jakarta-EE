package com.hha.online.shop.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;

import com.hha.online.shop.entity.Product;
import com.hha.online.shop.service.ProductService;

@Named
@ViewScoped
public class OwnerProductBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String product;
	private int priceFrom;
	private int priceTo;

	private List<Product> list;
	
	@Inject
	private MyShopBean ownerShop;

	@Inject
	private ProductService service;
	
	private Part inputFile;
	
	@PostConstruct
	private void init() {
		search();
	}
	
	public void search() {

		try {
			
			list = service.search(ownerShop.getShop(), product, priceFrom, priceTo);
			
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
		}
	}
	
	public void upload() {

		service.upload(ownerShop.getShop(), inputFile);
		search();
		
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public int getPriceFrom() {
		return priceFrom;
	}

	public void setPriceFrom(int priceFrom) {
		this.priceFrom = priceFrom;
	}

	public int getPriceTo() {
		return priceTo;
	}

	public void setPriceTo(int priceTo) {
		this.priceTo = priceTo;
	}

	public List<Product> getList() {
		return list;
	}

	public Part getInputFile() {
		return inputFile;
	}

	public void setInputFile(Part inputFile) {
		this.inputFile = inputFile;
	}
	
	
	
}
