package com.hha.orders.model.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.servlet.http.Part;

import com.hha.orders.model.entity.Brand;
import com.hha.orders.model.entity.Category;
import com.hha.orders.model.entity.Item;
import com.hha.orders.model.entity.Product;

@LocalBean
@Stateless
public class ProductService {

	@PersistenceContext
	private EntityManager em;

	public List<Item> search(int category, int brand, String product) {

		StringBuilder sb = new StringBuilder("select i from Item i where 1 = 1");
		
		Map<String, Object> params = new HashMap<>();
		
		if(category != 0) {
			sb.append(" and i.product.brand.category.id = :category");
			params.put("category", category);
		}
		if(brand != 0) {
			sb.append(" and i.product.brand.id = :brand");
			params.put("brand", brand);
		}
		if(null != product && !product.isBlank()) {
			sb.append(" and lower(i.product.name) like lower(:product)");
			params.put("product", product.concat("%"));
		}
		
		TypedQuery<Item> query = em.createQuery(sb.toString(), Item.class);
		
		for(String key : params.keySet()) {
			query.setParameter(key, params.get(key));
		}
		
		return query.getResultList();
	}

	@Asynchronous
	public Future<Boolean> uploadProducts(Part part) {

		try (BufferedReader br = new BufferedReader(new InputStreamReader(part.getInputStream()))){
			
			String line = null;
			while(null != (line = br.readLine())) {
				
				createItem(line);				
			}
			
			Thread.sleep(10000);
			
			return new AsyncResult<Boolean>(true);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new AsyncResult<Boolean>(false);
	}
	
	private void createItem(String line) {

		String[] array = line.split("\t");
		
		if(array.length == 7) {
			
			Category category = findCategory(array[0]);
			
			Brand brand = findBrand(category, array[1]);
			
			Product product = findProduct(brand, array[2]);
			
			Item item = findItem(product, array[3]);
			
			item.setUnit(Integer.parseInt(array[4]));
			item.setPrice(Integer.parseInt(array[5]));
			item.setAgentPrice(Integer.parseInt(array[6]));
			
				
		}
		
	}

	private Item findItem(Product product, String name) {

		TypedQuery<Item> query = em.createNamedQuery("Item.findByNameAndProduct", Item.class)
				.setParameter("name", name)
				.setParameter("product", product.getId());
		
		Item item = getSingleResult(query);
		
		if(null == item) {
			item = new Item();
			item.setName(name);
			item.setProduct(product);
			em.persist(item);
		}
		
		return item;
	}

	private Product findProduct(Brand brand, String name) {
		
		TypedQuery<Product> query = em.createNamedQuery("Product.findByNameAndBrand", Product.class)
				.setParameter("name", name)
				.setParameter("brand", brand.getId());
		
		Product product = getSingleResult(query);
		
		if(null == product) {
			product = new Product();
			product.setName(name);
			product.setBrand(brand);
			em.persist(product);
		}
		return product;
	}

	private Brand findBrand(Category category, String name) {

		TypedQuery<Brand> query = em.createNamedQuery("Brand.findByNameAndCategory", Brand.class)
				.setParameter("name", name)
				.setParameter("category", category.getId());
		
		Brand brand = getSingleResult(query);
		
		if(null == brand) {
			brand = new Brand();
			brand.setName(name);
			brand.setCategory(category);
			em.persist(brand);
		}
		
		return brand;
	}

	private Category findCategory(String name) {

		TypedQuery<Category> query = em.createNamedQuery("Category.findByName", Category.class)
				.setParameter("name", name);
		
		Category category = getSingleResult(query);
		
		if(null == category) {
			category = new Category();
			category.setName(name);
			em.persist(category);
		}
		
		return category;
	}

	private <T> T getSingleResult(TypedQuery<T> query) {
		
		try {
			return query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}
}
