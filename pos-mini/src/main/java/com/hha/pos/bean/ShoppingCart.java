package com.hha.pos.bean;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

import com.hha.pos.entity.Product;
import com.hha.pos.entity.SaleDetail;

@Named
@SessionScoped
public class ShoppingCart implements Serializable{

	private static final long serialVersionUID = 1L;

	private Map<Product, Integer> cart;
	
	@PostConstruct
	public void init() {
		cart = new LinkedHashMap<>();
	}
	
	// Get All Items Count
	@Named("allItmesCount")
	@Produces
	public int getAllItemsCount() {		
		return cart.values().stream().mapToInt(p -> p).sum();
	}
	
	// Get Total Price in Shopping cart
	@Named
	@Produces
	public int getTotal() {
		return cart.entrySet().stream().mapToInt(entry -> 
					entry.getKey().getPrice() * entry.getValue()).sum();
	}
	
	// Convert Map to Sale Detail List
	public List<SaleDetail> getOrders(){
		
		return cart.entrySet().stream().map(e ->{
				SaleDetail saleDetail = new SaleDetail();
				saleDetail.setProduct(e.getKey());
				saleDetail.setQuantity(e.getValue());
				return saleDetail;
		
			}).collect(Collectors.toList());
	}
	
	public void addProduct(Product product) {
		
		if(cart.containsKey(product)) {
			int count = cart.get(product);
			cart.put(product, count + 1);
		}else {
			cart.put(product, 1);
		}
		
	}
	
	public void clear() {
		cart.clear();
	}

	public Map<Product, Integer> getCart() {
		return cart;
	}

	public void setCart(Map<Product, Integer> cart) {
		this.cart = cart;
	}
	
	
}
