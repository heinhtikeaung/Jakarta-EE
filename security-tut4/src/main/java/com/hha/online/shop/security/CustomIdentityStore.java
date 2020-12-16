package com.hha.online.shop.security;

import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStore;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;

import com.hha.online.shop.AppCommonException;
import com.hha.online.shop.entity.Member;
import com.hha.online.shop.service.MemberService;

@ApplicationScoped
public class CustomIdentityStore implements IdentityStore{

	@Inject
	private Pbkdf2PasswordHash passwordEncoder;
	
	@Inject
	private MemberService service;
	
	@Inject
	private Event<Member> event;
	
	@Override
	public CredentialValidationResult validate(Credential credential) {
		
		// Login information
		UsernamePasswordCredential usernamePasswordCredential = (UsernamePasswordCredential) credential;
		
		Member member = service.findByEmail(usernamePasswordCredential.getCaller());
		
		if(null == member) {
			throw new AppCommonException("There is no user with this email address.");
		}
		
		if(!passwordEncoder.verify(usernamePasswordCredential.getPasswordAsString().toCharArray(), 
				member.getPassword())) {
			throw new AppCommonException("Invalid Password.");
		}
		
		if(member.isDeleted()) {
			throw new AppCommonException("Invalid Member. Please contact to admin.");
		}
		
		event.fire(member);
		
		return new CredentialValidationResult(member.getEmail(), Set.of(member.getRole().name()));
	}
}
