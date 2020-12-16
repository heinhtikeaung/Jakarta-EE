package com.hha.online.shop.bean;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named
@RequestScoped
public class LogoutBean {

	public String logout() {
		
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		
		return "/index?faces-redirect=true";
	}
}
