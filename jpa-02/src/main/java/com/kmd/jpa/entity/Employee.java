package com.kmd.jpa.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.REMOVE;

@Entity
@Data
public class Employee implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	@OneToMany(mappedBy = "employee", cascade = { PERSIST, MERGE, REMOVE })
	private List<Qualification> qualifications = new ArrayList<>();
	
	public void save(Qualification qualification) {
		qualification.setEmployee(this);
		qualifications.add(qualification);
	}
	
	public void remove(Qualification qualification) {
		qualification.setEmployee(null);
		qualifications.remove(qualification);
	}
}
