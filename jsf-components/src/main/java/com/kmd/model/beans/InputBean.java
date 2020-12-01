package com.kmd.model.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.kmd.model.Developer;
import com.kmd.model.Developer.Gender;

@Named
@ViewScoped
public class InputBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private Developer developer;

	private List<Developer> developers;

	@PostConstruct
	private void init() {
		developer = new Developer();
		developers = new ArrayList<>();
	}

	public void save() {
		developers.add(developer);
		developer = new Developer();
	}

	public Developer getDeveloper() {
		return developer;
	}

	public void setDeveloper(Developer developer) {
		this.developer = developer;
	}

	public List<Developer> getDevelopers() {
		return developers;
	}

	public Gender[] getGenders() {
		return Gender.values();
	}
}
