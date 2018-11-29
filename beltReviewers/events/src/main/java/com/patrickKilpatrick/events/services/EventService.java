package com.patrickKilpatrick.events.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patrickKilpatrick.events.models.Event;
import com.patrickKilpatrick.events.models.Message;
import com.patrickKilpatrick.events.repositories.EventRepository;

@Service
public class EventService {
	@Autowired
	private EventRepository eventRepo;
	
	public void createEvent(Event event) {
		eventRepo.save(event);
	}
	
	public void updateEvent(Event event) {
		eventRepo.save(event);
	}
	
	public Optional<Event> getEvent(Long id) {
		return eventRepo.findById(id);
	}
	
	public void deleteEvent(Long id) {
		eventRepo.deleteById(id);
	}
	
	public List<Event> getAll() {
		return eventRepo.findAll();
	}
	
	public List<Message> getMessages(Long id) {
		Event event = eventRepo.findById(id).orElse(null);
		return event.getMessages();
	}
	
}
