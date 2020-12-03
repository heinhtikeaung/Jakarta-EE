package com.hha.library.beans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.annotation.ManagedProperty;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.hha.library.dto.BookDTO;
import com.hha.library.entity.Author;
import com.hha.library.entity.Book;
import com.hha.library.service.AuthorService;
import com.hha.library.service.BookService;

@Named
@ViewScoped
public class BookEditBean implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	@ManagedProperty("#{param.author}")
	private int authorId;
	
	private Book book;
	
	private List<BookDTO> list;
	
	private Author author;
	
	@Inject
	private AuthorService authorService;
	
	@Inject
	private BookService bookService;
	
	@PostConstruct
	public void init() {

		if(authorId != 0) {
			author = authorService.findById(authorId);
			list = getBookList();
		}else {
			author = new Author();
		}		
		book = new Book();		
	}
	
	public List<BookDTO> getBookList() {
		return bookService.findByAuthorId(authorId);
	}
	
	public void save() {
		
		book.setAuthor(author);
		bookService.save(book);
		
		book = new Book();
		list = getBookList();
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public List<BookDTO> getList() {
		return list;
	}

	public Author getAuthor() {
		return author;
	}

	public int getAuthorId() {
		return authorId;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public void setList(List<BookDTO> list) {
		this.list = list;
	}
	
	
}
