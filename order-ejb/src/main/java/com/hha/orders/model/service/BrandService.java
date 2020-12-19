package com.hha.orders.model.service;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.hha.orders.model.entity.Brand;

@LocalBean
@Stateless
public class BrandService {

	@PersistenceContext
	private EntityManager em;

	public List<Brand> findByCategoryId(int category) {

		return em.createNamedQuery("Brand.findByCategoryId", Brand.class)
				.setParameter("category", category)
				.getResultList();
	}

}
