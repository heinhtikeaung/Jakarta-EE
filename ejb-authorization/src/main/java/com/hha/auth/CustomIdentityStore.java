package com.hha.auth;

import java.util.List;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStore;

@ApplicationScoped
public class CustomIdentityStore implements IdentityStore{
	
	private List<Member> list = List.of(
			new Member("admin", "admin", "Admin"),
			new Member("member", "member", "Member"));

	@Override
	public CredentialValidationResult validate(Credential credential) {

		UsernamePasswordCredential usernamePasswordCredential = (UsernamePasswordCredential) credential;
		
		for(Member m : list) {
			if(m.getName().equals(usernamePasswordCredential.getCaller()) && 
					m.getPassword().equals(usernamePasswordCredential.getPasswordAsString())) {
				return new CredentialValidationResult(m.getName(), Set.of(m.getRole()));
			}
		}
		
		return CredentialValidationResult.INVALID_RESULT;
	}
	
	class Member{
		String name;
		String password;
		String role;
		
		public Member(String name, String password, String role) {
			super();
			this.name = name;
			this.password = password;
			this.role = role;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getRole() {
			return role;
		}
		public void setRole(String role) {
			this.role = role;
		}
	
		
	}
}
