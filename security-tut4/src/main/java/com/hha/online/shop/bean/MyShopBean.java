package com.hha.online.shop.bean;

import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;

import com.hha.online.shop.entity.Member;
import com.hha.online.shop.entity.Shop;
import com.hha.online.shop.service.ShopService;
import com.hha.online.shop.utils.ImageUtils;

@Named
@SessionScoped
public class MyShopBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Shop shop;
	
	@Named("loginUser")
	@Inject
	private Member owner;
	
	@Inject
	private ShopService service;
	
	@Inject
	private ImageUtils coverImage;
	
	@PostConstruct
	private void init() {
		shop = service.findByOwner(owner);  
	}
	
	public void uploadPhoto() {
		
		// don't need to check null if use ajax
		if(null != coverImage.getInputFile()) {
			
			try {
				
				// get Image File Name
				String coverFileName = String.format("cover_%s.%s", 
						LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")), 
						coverImage.getExtension());
				
				// get Image directory
				ServletContext context = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
				String des = context.getRealPath("/resources/images");
				
				// copy Image
				Files.copy(coverImage.getInputStream(), Paths.get(des, coverFileName), StandardCopyOption.REPLACE_EXISTING);

				// update to entity
				shop.setCoverPhoto(coverFileName);
				
				// save to database
				service.save(shop);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}

	public void editShop() {
		shop = service.save(shop);
	}
	
	public void editShopStatus() {
		shop.setPublished(!shop.isPublished());
		shop = service.save(shop);
	}
	
	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public ImageUtils getCoverImage() {
		return coverImage;
	}

	public void setCoverImage(ImageUtils coverImage) {
		this.coverImage = coverImage;
	}
	
	
}
