package com.kmd.jpa.entity;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class MessageBean {

	private Message message;
	
	private List<Message> messageList;
	
	@Inject
	private MessageService service;
	
	@PostConstruct
	private void init() {
		messageList = service.getAllMessage();
		message = new Message();
	}
	
	public void create() {
		service.create(message);
		messageList.add(message);
	}

	public Message getMessage() {
		return message;
	}

	public List<Message> getMessageList() {
		return messageList;
	}
	
	
}
