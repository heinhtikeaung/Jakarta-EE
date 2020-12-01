package com.kmd.model.beans;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import com.kmd.model.Developer;

@Named
@RequestScoped
public class SelectBean {

	private Developer developer;
	
	private List<String> hobbies;
	
	private List<String> foods;
	
	@PostConstruct
	private void init() {
		hobbies = Arrays.asList("Game", "Programming", "Football");
		foods = Arrays.asList("Coffee", "Chicken", "Pizza");
		developer = new Developer();
	}

	public Developer getDeveloper() {
		return developer;
	}

	public void setDeveloper(Developer developer) {
		this.developer = developer;
	}

	public List<String> getHobbies() {
		return hobbies;
	}

	public List<String> getFoods() {
		return foods;
	}
	
	

}
