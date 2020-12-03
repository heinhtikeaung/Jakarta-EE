package com.hha.library.service;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.hha.library.entity.Author;

@LocalBean
@Stateless
public class AuthorService {

	@PersistenceContext
	private EntityManager em;

	public List<Author> getAll() {

		return em.createNamedQuery("Author.getAll", Author.class).getResultList();
	}

	public void save(Author author) {

		em.persist(author);
	}

	public Author findById(int authorId) {

		return em.find(Author.class, authorId);
	}
	
	
}
