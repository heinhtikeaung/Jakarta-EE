package com.hha.online.shop.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
public class Product implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(optional = false)
	private Shop shop;

	private String name; 	
	
	private boolean used;
	private int price;
	private boolean soldOut;
	
	private String brand;
	
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "property_photo")
	private List<String> photos;
	
	@ElementCollection
	@CollectionTable(name = "property_prop")
	private List<Property> properties;
	
	@Transient
	private int photoIndex;
	
	public Product() {
		photos = new ArrayList<>();
		properties = new ArrayList<>();
	}
	
	/**
	 * Product Cover Image
	 * @return
	 */
	public String getCoverImage() {
		
		if(photos.size() > photoIndex) {
			return photos.get(photoIndex);
		}
		
		return "No-Image.jpg";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isUsed() {
		return used;
	}

	public void setUsed(boolean used) {
		this.used = used;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public boolean isSoldOut() {
		return soldOut;
	}

	public void setSoldOut(boolean soldOut) {
		this.soldOut = soldOut;
	}

	public List<String> getPhotos() {
		return photos;
	}

	public void setPhotos(List<String> photos) {
		this.photos = photos;
	}

	public List<Property> getProperties() {
		return properties;
	}

	public void setProperties(List<Property> properties) {
		this.properties = properties;
	}

	public int getPhotoIndex() {
		return photoIndex;
	}

	public void setPhotoIndex(int photoIndex) {
		this.photoIndex = photoIndex;
	}
	
	

}
