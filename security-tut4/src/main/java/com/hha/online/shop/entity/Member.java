package com.hha.online.shop.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity
@NamedQuery(name = "Member.findCountbyEmail", query = "select COUNT(m) from Member m where m.email = :email")
@NamedQuery(name = "Member.count", query = "select COUNT(m) from Member m")
@NamedQuery(name = "Member.findByEmail", 
	query = "select m from Member m where m.email = :email")
public class Member implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotEmpty(message = "Enter Name.")
	private String name;
	
	@Email(message = "Enter Valid Email Address.")
	@Column(nullable = false, unique = true)
	private String email;
	
	@NotEmpty(message = "Enter Password")
	private String password;
	
	private Role role;
	
	private boolean deleted;
	
	public enum Role{
		Admin, Owner, Customer;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
	
}
