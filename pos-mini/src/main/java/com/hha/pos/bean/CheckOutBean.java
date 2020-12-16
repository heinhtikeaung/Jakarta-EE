package com.hha.pos.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.hha.pos.entity.Sale;
import com.hha.pos.services.SaleService;

@Named
@ConversationScoped
public class CheckOutBean implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private ShoppingCart cart;
	
	@Inject
	private Conversation conversation;
	
	private Sale sale;
	
	@Inject
	private SaleService service;
	
	@PostConstruct
	private void init() {
		if(conversation.isTransient()) {
			conversation.begin();
		}
		sale = new Sale();
	}
	
	public String checkOut() {
		
		service.save(cart.getOrders(), sale);
		
		cart.clear();
		
		return "/views/sales?faces-redirect=true";
	}

	public ShoppingCart getCart() {
		return cart;
	}

	public void setCart(ShoppingCart cart) {
		this.cart = cart;
	}

	public Sale getSale() {
		return sale;
	}

	public void setSale(Sale sale) {
		this.sale = sale;
	}
	
	
}
