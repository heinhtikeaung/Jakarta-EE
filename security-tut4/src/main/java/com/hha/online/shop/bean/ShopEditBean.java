package com.hha.online.shop.bean;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.hha.online.shop.entity.Member;
import com.hha.online.shop.entity.Shop;
import com.hha.online.shop.service.ShopService;

@Named
@RequestScoped
public class ShopEditBean {

	private Shop shop;
	
	@Inject
	private ShopService service;
	
	@PostConstruct
	private void init() {
		shop = new Shop();
		shop.setOwner(new Member());
	}
	
	public String create() {
		
		service.create(shop);
		
		return "/index";
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}
	
	
}
