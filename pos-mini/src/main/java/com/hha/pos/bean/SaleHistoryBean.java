package com.hha.pos.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.hha.pos.entity.Sale;
import com.hha.pos.services.SaleService;

@Named
@RequestScoped
public class SaleHistoryBean {

	@Inject
	private SaleService service;
	
	private List<Sale> saleList;
	
	@PostConstruct
	private void init() {
		saleList = service.getAll();
	}
	
	public List<Sale> getSaleList() {
		return saleList;
	}
	
	// Get Quantity from All Record
	public int getQuantity() {
		return saleList.stream().mapToInt(s -> s.getTotalQuantity()).sum();
	}
	
	// Get Price from All Record
	public int getPrice() {
		return saleList.stream().mapToInt(s -> s.getTotalPrice()).sum();
	}
	
}
