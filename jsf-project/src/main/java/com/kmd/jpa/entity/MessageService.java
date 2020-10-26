package com.kmd.jpa.entity;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class MessageService {

	@PersistenceContext
	private EntityManager entityManager;

	public List<Message> getAllMessage() {

		return entityManager.createQuery("from Message m", Message.class).getResultList();
	}

	public void create(Message message) {

		entityManager.persist(message);
	}
}
