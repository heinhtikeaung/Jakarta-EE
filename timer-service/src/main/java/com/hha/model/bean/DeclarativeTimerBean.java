package com.hha.model.bean;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import com.hha.model.entity.Message;
import com.hha.model.service.MessageContainer;

@Named
@RequestScoped
public class DeclarativeTimerBean {

	@EJB
	private MessageContainer messageContainer;
	
	public List<Message> getMessage() {
		return messageContainer.getList();
	}
}
