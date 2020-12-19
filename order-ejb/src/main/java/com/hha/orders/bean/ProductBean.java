package com.hha.orders.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.Part;

import com.hha.orders.model.entity.Brand;
import com.hha.orders.model.entity.Category;
import com.hha.orders.model.entity.Item;
import com.hha.orders.model.service.BrandService;
import com.hha.orders.model.service.CategoryService;
import com.hha.orders.model.service.ItemService;
import com.hha.orders.model.service.ProductService;

@Named
@ViewScoped
public class ProductBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int category;
	
	private int brand;
	
	private String product;
	
	private List<Item> list;
	
	private List<Category> categories;
	
	private List<Brand> brands;
	
	private Part part;
	
	@EJB
	private CategoryService categoryService;
	
	@EJB
	private ProductService productService;
	
	@EJB
	private BrandService brandService;
	
	@EJB
	private ItemService itemService;
	
	@PostConstruct
	private void init() {
		categories = categoryService.getAll();
		search();
	}
	
	public void search() {
		list = productService.search(category, brand, product);
	}
	
	public void upload() {
		itemService.uploadProducts(part);
		search();
	}
	
	public void findByCategoryId() {
		brands = brandService.findByCategoryId(category);
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public int getBrand() {
		return brand;
	}

	public void setBrand(int brand) {
		this.brand = brand;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public Part getPart() {
		return part;
	}

	public void setPart(Part part) {
		this.part = part;
	}

	public List<Item> getList() {
		return list;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public List<Brand> getBrands() {
		return brands;
	}
	

}
