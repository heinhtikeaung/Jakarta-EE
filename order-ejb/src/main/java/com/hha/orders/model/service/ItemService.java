package com.hha.orders.model.service;

import java.util.concurrent.Future;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.Part;

@Stateless
@LocalBean
public class ItemService {

	@PersistenceContext
	private EntityManager em;

	@Inject
	private ProductService productService;
	
	public void uploadProducts(Part part) {

		Future<Boolean> result = productService.uploadProducts(part);
		
		// do other stuff
		System.out.println("Asynchronous");		
		
		try {
			if(result.get()) {
				System.out.println("Upload Complete...");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	
}
