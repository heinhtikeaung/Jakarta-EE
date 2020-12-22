package com.hha.auth;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named
@RequestScoped
public class AuthorizationBean {

	@EJB
	private BusinessSessionBean service;
	
	public void forAdmin() {
		System.out.println("Name: " + service.getUserName());
		service.forAdmin();
	}
	
	public void forMember() {
		System.out.println("Name: " + service.getUserName());
		service.forMember();
	}
	
	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "index.xhtml?faces-redirect=true";
	}
}
