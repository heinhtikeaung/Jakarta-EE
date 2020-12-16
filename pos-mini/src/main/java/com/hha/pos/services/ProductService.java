package com.hha.pos.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.hha.pos.entity.Product;

@LocalBean
@Stateless
public class ProductService {

	@PersistenceContext
	private EntityManager em;

	public List<Product> findByCategoryId(int categoryId) {

		return em.createNamedQuery("Product.findByCategoryId", Product.class)
				.setParameter("categoryId", categoryId)
				.getResultList();
	}
}
