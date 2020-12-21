package com.hha.model.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.ScheduleExpression;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;

import com.hha.model.entity.TimerId;

@LocalBean
@Stateless
public class ProgrammativeTimerService {

	@Resource
	private TimerService timerService;
	
	@EJB
	private MessageContainer messageContainer;
	
	public void createTimer(TimerId timerId) {
		
		ScheduleExpression timer = new ScheduleExpression()
				.hour("*")
				.minute("*")
				.second(String.format("*/%d", timerId.getInterval()));
		
		timerId.setTime(LocalDateTime.now());
		
		timerService.createCalendarTimer(timer, new TimerConfig(timerId, false));
	}
	
	@Timeout
	public void generateMessage(Timer timer) {
		
		TimerId timerId = (TimerId) timer.getInfo();
		String format = timerId.getTime().format(DateTimeFormatter.ofPattern("HH:ss"));
		
		messageContainer.addMessage(String.format("%s %s", timerId.getMessage(), format));
		
	}
}
