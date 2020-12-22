package com.hha.auth;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.LocalBean;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

@Stateless
@LocalBean
@PermitAll
public class BusinessSessionBean {

	@Resource
	private SessionContext ctx;
	
	public String getUserName() {
		
		if(ctx.isCallerInRole("Admin")) {
			System.out.println("Use as Admin.");
		}
		if(ctx.isCallerInRole("Member")) {
			System.out.println("Use as Member");
		}
		
		return ctx.getCallerPrincipal().getName();
		
	}
	
	@RolesAllowed("Admin")
	public void forAdmin() {
		System.out.println("Admin Function.");
	}
	
	@RolesAllowed({"Admin", "Member"})
	public void forMember() {
		System.out.println("Member Function.");
	}
}
