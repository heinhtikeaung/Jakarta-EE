package com.hha.model.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class TimerId implements Serializable{

	private static final long serialVersionUID = 1L;

	private String message;
	
	private LocalDateTime time;
	
	private int interval;
}
