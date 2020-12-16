package com.hha.online.shop.bean;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.SecurityContext;
import javax.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.hha.online.shop.entity.Member;
import com.hha.online.shop.service.MemberService;

@Named
@RequestScoped
public class LoginBean {

	@NotEmpty(message = "Enter Email Address.")
	@Email(message = "Enter Valid Email Address.")
	private String email;
	
	@NotEmpty(message = "Enter Password.")
	private String password;
	
	@Inject
	private ExternalContext context;
	
	@Inject
	private SecurityContext security;
	
	@Inject
	private MemberService service;
	
	public String login() {
		 
		try {
			
			HttpServletRequest request =  (HttpServletRequest) context.getRequest();
			HttpServletResponse response =  (HttpServletResponse) context.getResponse();
			
			UsernamePasswordCredential usernamePasswordCredential = new UsernamePasswordCredential(email, password);
			
			AuthenticationStatus status = security.authenticate(request, response, 
					AuthenticationParameters.withParams().credential(usernamePasswordCredential));
			
			if(status == AuthenticationStatus.SUCCESS) {
				
				Member member = service.findByEmail(usernamePasswordCredential.getCaller());
				
				return String.format("/%s/home?faces-redirect=true", 
						member.getRole().name().toLowerCase());
				
			}
			
			
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
		}
		
		return null;
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
	
}
