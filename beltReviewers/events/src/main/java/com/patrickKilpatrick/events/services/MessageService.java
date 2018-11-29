package com.patrickKilpatrick.events.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patrickKilpatrick.events.models.Event;
import com.patrickKilpatrick.events.models.Message;
import com.patrickKilpatrick.events.models.User;
import com.patrickKilpatrick.events.repositories.MessageRepository;

@Service
public class MessageService {
	@Autowired
	private MessageRepository messageRepo;
	
	public void createMessage(Message message, Event currentEvent, User currentUser) {
		message.setEvent(currentEvent);
		message.setUser(currentUser);
		messageRepo.save(message);
	}
}
