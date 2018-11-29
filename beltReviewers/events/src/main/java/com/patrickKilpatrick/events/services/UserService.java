package com.patrickKilpatrick.events.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.patrickKilpatrick.events.models.Event;
import com.patrickKilpatrick.events.models.User;
import com.patrickKilpatrick.events.repositories.RoleRepository;
import com.patrickKilpatrick.events.repositories.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private RoleRepository roleRepo;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public void createUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.addRole(roleRepo.findByName("Role_User"));
		userRepo.save(user);
	}
	
	public User findByUsername(String username) {
		return userRepo.findByUsername(username);
	}
	
	public void addEvent(User user, Event event) {
		user.addEvent(event);
		userRepo.save(user);
	}
	
	public void leaveEvent(User user, Event event) {
		user.removeEvent(event);
		userRepo.save(user);
	}
	
}
