package com.hha.online.shop.service;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;

import com.hha.online.shop.AppCommonException;
import com.hha.online.shop.entity.Member;

@Stateless
@LocalBean
public class MemberService {

	@PersistenceContext
	private EntityManager em;
	
	@Inject
	private Pbkdf2PasswordHash passwordEncoder;

	// For Authentication
	public Member findByEmail(String email) {

		TypedQuery<Member> query = em.createNamedQuery("Member.findByEmail", Member.class);
		query.setParameter("email", email);
		
		// getSingleResult will cause error
		List<Member> list = query.getResultList();
		
		for(Member m : list) {
			return m;
		}
		
		return null;
		
	}

	// Create Member
	public void createMember(Member member) {

		Long count = em.createNamedQuery("Member.findCountbyEmail", Long.class)
				.setParameter("email", member.getEmail())
				.getSingleResult();
		
		if(count > 0) {
			throw new AppCommonException("Your email is already registered.");
		}
		
		
		member.setPassword(passwordEncoder.generate(member.getPassword().toCharArray()));
		
		em.persist(member);
	}

	// For Admin User Initializer
	public Long findCount() {

		return em.createNamedQuery("Member.count", Long.class).getSingleResult();
	}
	
}
