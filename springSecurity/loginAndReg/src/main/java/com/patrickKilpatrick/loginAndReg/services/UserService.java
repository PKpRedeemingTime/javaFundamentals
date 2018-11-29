package com.patrickKilpatrick.loginAndReg.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.patrickKilpatrick.loginAndReg.models.User;
import com.patrickKilpatrick.loginAndReg.repositories.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public User findByUsername(String username) {
		return userRepo.findByUsername(username);
	}
	
	public User addUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		return userRepo.save(user);
	}
}
