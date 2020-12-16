package com.hha.online.shop.security;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

import com.hha.online.shop.entity.Member;

@SessionScoped
public class LoginUserHolder implements Serializable{

	private static final long serialVersionUID = 1L;

	@Named("loginUser")
	@Produces
	private Member member;
	
	/**
	 * when click username in menu
	 * @return
	 */
	@Named
	@Produces
	public String getLoginUserHome() {		
		return String.format("/%s/home?faces-redirect=true", member.getRole().name().toLowerCase());
	}
	
	/**
	 * For Navigation link
	 * @return
	 */
	@Named
	@Produces
	public String getLoginUserRole() {
		
		if(null != member) {
			return member.getRole().name();	
		}
		
		return null;
	}
	
	public void setMember(@Observes Member member) {
		this.member = member; 
	}
	
	public Member getMember() {
		return member;
	}
}
