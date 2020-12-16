package com.hha.pos.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity
@NamedQuery(name = "Sale.getAll", query = "select s from Sale s")
public class Sale implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotEmpty(message = "Enter Name.")
	private String customer;
	
	@NotEmpty(message = "Enter Phone Number")
	private String phone;
	
	@Email(message = "Enter Valid Email Address.")
	private String email;
	
	private LocalDateTime saleDate;
	
	@OneToMany(mappedBy = "sale",fetch = FetchType.EAGER)
	private List<SaleDetail> details;
	
	@PrePersist
	public void prePersist() {
		saleDate = LocalDateTime.now();
	}

	// Get Price from Single records
	public int getTotalPrice() {
		return details.stream().mapToInt(d -> d.getQuantity() * d.getProduct().getPrice()).sum();
	}
	
	// Get Quantity from Single records
	public int getTotalQuantity() {
		return details.stream().mapToInt(d -> d.getQuantity()).sum();
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDateTime getSaleDate() {
		return saleDate;
	}

	public void setSaleDate(LocalDateTime saleDate) {
		this.saleDate = saleDate;
	}

	public List<SaleDetail> getDetails() {
		return details;
	}

	public void setDetails(List<SaleDetail> details) {
		this.details = details;
	}
	
}
