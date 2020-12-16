package com.hha.online.shop;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import com.hha.online.shop.entity.Member;
import com.hha.online.shop.entity.Member.Role;
import com.hha.online.shop.service.MemberService;

@Startup
@Singleton
public class AdminUserInitializer {

	@Inject
	private MemberService service;
	
	@PostConstruct
	private void init() {
		
		Long count = service.findCount();
		
		if(count == 0) {
			Member member = new Member();
			member.setName("Hein Htike Aung");
			member.setEmail("hein@gmail.com");
			member.setPassword("admin");
			member.setRole(Role.Admin);
			
			service.createMember(member);
			
		}
	}
}
