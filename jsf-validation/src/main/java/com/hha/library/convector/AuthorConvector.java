package com.hha.library.convector;

import javax.enterprise.context.Dependent;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import com.hha.library.entity.Author;
import com.hha.library.service.AuthorService;

@Named
@Dependent
public class AuthorConvector implements Converter<Author>{

	@Inject
	private AuthorService service;
	
	@Override
	public Author getAsObject(FacesContext context, UIComponent component, String value) {
		
		if(value != null && !value.isBlank()) {			
			Author author =service.findById(Integer.parseInt(value)); 
			return author;
		}
		
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Author value) {

		if(null != value) {
			return String.valueOf(value.getId());
		}
		
		return null;
	}

}
