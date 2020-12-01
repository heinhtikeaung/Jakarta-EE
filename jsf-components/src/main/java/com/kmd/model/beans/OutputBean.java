package com.kmd.model.beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class OutputBean {

	private String name = "Tun Tun";
	
	private String phone = "09343434";
	
	private String job = "Programmer";

	public String getName() {
		return name;
	}

	public String getPhone() {
		return phone;
	}

	public String getJob() {
		return job;
	}
	
	
}
