package com.hha.online.shop;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class AppCommonException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public AppCommonException(String message) {
		super(message);
	}

}
