package com.hha.pos.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.hha.pos.SaleException;
import com.hha.pos.entity.Sale;
import com.hha.pos.entity.SaleDetail;

@LocalBean
@Stateless
public class SaleService {

	@PersistenceContext
	private EntityManager em;

	public void save(List<SaleDetail> orders, Sale sale) {

		if(sale == null && orders.isEmpty()) {
			throw new SaleException("Check Shopping Cart");
		}
		
		em.persist(sale);
		
		for(SaleDetail detail : orders) {
			detail.setSale(sale);
			em.persist(detail);
		}
	}

	public List<Sale> getAll() {

		return em.createNamedQuery("Sale.getAll", Sale.class).getResultList();
	}

	public Sale findById(int saleId) {

		return em.find(Sale.class, saleId);
	}

	
}
