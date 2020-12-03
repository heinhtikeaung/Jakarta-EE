package com.hha.library.service;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.hha.library.dto.BookDTO;
import com.hha.library.entity.Book;

@LocalBean
@Stateless
public class BookService {

	@PersistenceContext
	private EntityManager em;
	
	public List<BookDTO> findByAuthorId(int authorId) {
		
		return em.createNamedQuery("Book.findByAuthorId", BookDTO.class)
				.setParameter("authorId", authorId).getResultList();
	}

	public void save(Book book) {

		em.persist(book);
	}

}
