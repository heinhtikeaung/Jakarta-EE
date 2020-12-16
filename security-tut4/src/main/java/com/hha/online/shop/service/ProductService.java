package com.hha.online.shop.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.servlet.http.Part;

import com.hha.online.shop.AppCommonException;
import com.hha.online.shop.entity.Product;
import com.hha.online.shop.entity.Shop;

@Stateless
@LocalBean
public class ProductService {

	@PersistenceContext
	private EntityManager em;

	public List<Product> search(Shop shop, String product, int priceFrom, int priceTo) {

		Map<String, Object> params = new HashMap<>();
		
		if(priceFrom > priceTo) {
			throw new AppCommonException("Invalid input! Price To must be greater than Price From.");
		}
		
		StringBuffer sb = new StringBuffer("select p from Product p where p.shop.id = :shopId");
		params.put("shopId", shop.getId());
		
		if(null != product && !product.isBlank()) {
			sb.append(" and lower(p.name) like lower(:name)");
			params.put("name", product.concat("%"));
		}
		
		if(priceFrom > 0) {
			sb.append(" and p.price >= :priceFrom");
			params.put("priceFrom", priceFrom);
		}
		
		if(priceTo > 0) {
			sb.append(" and p.price <= :priceTo");
			params.put("priceTo", priceTo);
		}
		
		TypedQuery<Product> query = em.createQuery(sb.toString(), Product.class);
		
		for(String key : params.keySet()) {
			query.setParameter(key, params.get(key));
		}
		
		return query.getResultList();
	}

	/**
	 * Product upload with file
	 * @param shop
	 * @param inputFile
	 */
	public void upload(Shop shop, Part inputFile) {

		try (BufferedReader br = new BufferedReader(new InputStreamReader(inputFile.getInputStream()))){
			
			String line = null;
			
			while(null != (line = br.readLine())) {
				String[] array = line.split("\t");
				
				Product product = new Product();
				product.setShop(shop);
				product.setName(array[0]); // Name
				product.setBrand(array[1]); // Brand
				product.setUsed(!"1".equals(array[2])); // Used
				product.setPrice(Integer.parseInt(array[3])); // Price
				
				em.persist(product);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Product findById(int id) {

		Product product = em.find(Product.class, id);
		// get product's properties in Managed State
		product.getProperties().forEach(p -> {});
		return product;
	}

	public void save(Product product) {

		if(product.getId() == 0) {
			em.persist(product);
		}else {
			em.merge(product);
		}
	}
}
