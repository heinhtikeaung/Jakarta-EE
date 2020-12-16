package com.hha.online.shop.bean;

import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.annotation.PostConstruct;
import javax.faces.annotation.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import com.hha.online.shop.entity.Product;
import com.hha.online.shop.entity.Property;
import com.hha.online.shop.service.ProductService;
import com.hha.online.shop.utils.ImageUtils;

@Named
@ViewScoped
public class ProductEditBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private Product product;
	
	@Inject
	private MyShopBean ownerShop;
	
	@Inject
	private ProductService service;
	
	@Inject
	@ManagedProperty("#{param.id}")
	private int id;
	
	@Inject
	private ImageUtils productImage;
	
	@PostConstruct
	private void init() {
		
		if(id == 0) {
			product = new Product();
			product.setShop(ownerShop.getShop());
		}else {
			product = service.findById(id);
		}
	}

	// Upload Product Photos
	public void uploadPhotos() {
		
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		
		// get Image Directory
		ServletContext context = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();		
		String imageFolder = context.getRealPath("/resources/images/");
		
		try {
			int index = 0;
			for(Part file : request.getParts()) {

				if(file.getName().equals(productImage.getInputFile().getName())) {
					
					// get Image File Name
					String imageName = String.format("photo_%d_%s_%d.%s", 
							ownerShop.getShop().getId(),
							LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")),
							++ index,
							productImage.getExtension());
					
					// copy Image
					Files.copy(file.getInputStream(), Paths.get(imageFolder, imageName), StandardCopyOption.REPLACE_EXISTING);
					
					// update to Entity
					product.getPhotos().add(imageName);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// Save Product
	public String save() {
		
		service.save(product);
		
		return "/owner/home?faces-redirect=true";
	}
	
	// Add new Product's Property
	public void addProperty() {
		product.getProperties().add(new Property());
	}
	
	// Remove Product's Property
	public void removeProperty(int index) {
		product.getProperties().remove(index);
	}
	
	// Change Product's Photo
	public void selectPhoto(int index) {
		product.setPhotoIndex(index);
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ImageUtils getProductImage() {
		return productImage;
	}

	public void setProductImage(ImageUtils productImage) {
		this.productImage = productImage;
	}
	
}
