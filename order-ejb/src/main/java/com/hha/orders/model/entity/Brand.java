package com.hha.orders.model.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

import lombok.Data;

@Entity
@Data
@NamedQuery(name = "Brand.findByCategoryId", 
	query = "select b from Brand b where b.category.id = :category")
@NamedQuery(name = "Brand.findByNameAndCategory", 
	query = "select b from Brand b where b.name = :name and b.category.id = :category")
public class Brand implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	@ManyToOne
	private Category category;
}
