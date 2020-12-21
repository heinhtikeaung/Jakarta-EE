package com.hha.model.bean;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import com.hha.model.entity.TimerId;
import com.hha.model.service.ProgrammativeTimerService;

@Named
@RequestScoped
public class ProgrammaticTimerBean {

	private TimerId timerId;
	
	@EJB
	private ProgrammativeTimerService programmativeTimerService;
	
	@PostConstruct
	private void init() {
		timerId = new TimerId();
	}
	
	public void create() {
		programmativeTimerService.createTimer(timerId);
	}

	public TimerId getTimerId() {
		return timerId;
	}

	public void setTimerId(TimerId timerId) {
		this.timerId = timerId;
	}
	
	
}
