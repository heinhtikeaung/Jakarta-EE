package com.hha.model.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import com.hha.model.entity.Message;

@LocalBean
@Singleton
@Startup
public class MessageContainer {

	private List<Message> list;
	
	@PostConstruct
	private void init() {
		list = new ArrayList<>();
	}
	
	public void addMessage(String message) {
		Message m = new Message();
		m.setId(list.size() + 1);
		m.setMessage(message);
		m.setTime(LocalDateTime.now());
		list.add(m);
	}

	public List<Message> getList() {

		return list;
	}
	
	
}
