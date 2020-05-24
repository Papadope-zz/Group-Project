package com.vbp.quizzery.ui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

import com.vbp.quizzery.shared.UserStorage;
import com.vbp.quizzery.shared.dto.MessageDto;

@RestController
public class MessageController {

	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;

	@MessageMapping("/chat/{to}")
	public void sendMessage(@DestinationVariable String to, MessageDto message) {
		System.out.println("handling send message: " + message + " to: " + to);

		simpMessagingTemplate.convertAndSend("/topic/messages/" + to, message);

		boolean isExists = UserStorage.getInstance().getUsers().contains(to);

		if (isExists) {

		}
	}

}
