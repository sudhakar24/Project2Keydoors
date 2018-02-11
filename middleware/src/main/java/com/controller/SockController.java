package com.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

import com.model.chat;

@Controller
public class SockController {
	private static final Log logger = LogFactory.getLog(SockController.class);

	private SimpMessagingTemplate messageTemplate;
	private  List<String> users= new ArrayList<String>();
	
	@Autowired
	public SockController(SimpMessagingTemplate messageTemplate) {
		super();
		this.messageTemplate = messageTemplate;
	}
	
	@SubscribeMapping("/join/{username}")
	public List<String> join(@DestinationVariable("username") String username){
		if(!users.contains(username)){
			users.add(username);
		}
		messageTemplate.convertAndSend("/topic/join",username);
		return users;
	}
	
	@MessageMapping(value="/chat")
	public void chatsReceived(chat chat){
		if(chat.getTo().equals("all")){
			messageTemplate.convertAndSend("/queue/chats",chat);
		}
		else{
			messageTemplate.convertAndSend("/queue/chats/"+chat.getFrom(),chat);
			messageTemplate.convertAndSend("/queue/chats/"+chat.getTo(),chat);
		}
	}
}
