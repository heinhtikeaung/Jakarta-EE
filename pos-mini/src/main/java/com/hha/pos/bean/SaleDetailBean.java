package com.hha.pos.bean;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.annotation.ManagedProperty;
import javax.inject.Inject;
import javax.inject.Named;

import com.hha.pos.entity.Sale;
import com.hha.pos.services.SaleService;

@Named
@RequestScoped
public class SaleDetailBean {

	@Inject
	private SaleService service;
	
	@Inject
	@ManagedProperty("#{param.sale}")
	private int saleId;
	
	private Sale sale;
	
	@PostConstruct
	private void init() {
		sale = service.findById(saleId);
	}
	
	public Sale getSale() {
		return sale;
	}
}
