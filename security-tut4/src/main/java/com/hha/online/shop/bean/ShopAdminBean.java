package com.hha.online.shop.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.hha.online.shop.entity.Shop;
import com.hha.online.shop.service.ShopService;

@Named
@RequestScoped
public class ShopAdminBean {

	
	private String shop;
	
	private String type;
	
	private String owner;
	
	private List<Shop> shops;
	
	@Inject
	private ShopService service;
	
	@PostConstruct
	private void init() {
		search();
	}
	
	public void search() {
		shops = service.search(shop, type, owner);
	}

	public String getShop() {
		return shop;
	}

	public void setShop(String shop) {
		this.shop = shop;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public List<Shop> getShops() {
		return shops;
	}
	
}

