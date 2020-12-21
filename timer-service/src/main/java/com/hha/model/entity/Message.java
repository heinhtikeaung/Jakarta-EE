package com.hha.model.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Message implements Serializable{

	private static final long serialVersionUID = 1L;

	private int id;
	
	private LocalDateTime time;
	
	private String message;
}
