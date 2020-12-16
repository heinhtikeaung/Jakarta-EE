package com.hha.online.shop.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.hha.online.shop.entity.Member;
import com.hha.online.shop.entity.Member.Role;
import com.hha.online.shop.entity.Shop;

import javax.persistence.EntityManager;

@Stateless
@LocalBean
public class ShopService {

	@PersistenceContext
	private EntityManager em;

	@Inject
	private MemberService memberService;
	
	public void create(Shop shop) {

		Member owner = shop.getOwner();
		owner.setRole(Role.Owner);
		
		// don't use cascade (use own method)
		memberService.createMember(owner);
		
		em.persist(shop);
	}

	public List<Shop> search(String shop, String type, String owner) {

		StringBuffer sb = new StringBuffer("select s from Shop s where 1 = 1 ");
		
		Map<String, String> params = new HashMap<>();
		
		if(null != shop && !shop.isBlank()) {
			sb.append("and lower(s.name) like lower(:name)");
			params.put("name", shop.concat("%"));
		}
		
		if(null != type && !type.isBlank()) {
			sb.append("and lower(s.type) like lower(:type)");
			params.put("type", "%".concat(type.concat("%")));
		}
		
		if(null != owner && !owner.isBlank()) {
			sb.append("and lower(s.owner.name) like lower(:owner)");
			params.put("owner", owner.concat("%"));
		}
		
		TypedQuery<Shop> query = em.createQuery(sb.toString(), Shop.class);
		
		for(String key : params.keySet()) {
			query.setParameter(key, params.get(key));
		}
		
		return query.getResultList();
	}

	public Shop findByOwner(Member owner) {

		return em.createNamedQuery("Shop.findbyOwner", Shop.class)
				.setParameter("loginId", owner.getEmail())
				.getSingleResult();
	}

	public Shop save(Shop shop) {
		
		return em.merge(shop);
	}
}
