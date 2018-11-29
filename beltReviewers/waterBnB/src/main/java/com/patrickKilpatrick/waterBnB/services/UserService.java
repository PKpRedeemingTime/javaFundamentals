package com.patrickKilpatrick.waterBnB.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.patrickKilpatrick.waterBnB.models.User;
import com.patrickKilpatrick.waterBnB.repositories.RoleRepository;
import com.patrickKilpatrick.waterBnB.repositories.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private RoleRepository roleRepo;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public void createHostUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.addRole(roleRepo.findByName("Role_Host"));
		userRepo.save(user);
	}
	
	public void createGuestUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.addRole(roleRepo.findByName("Role_Guest"));
		userRepo.save(user);
	}
	
	public User findByUsername(String username) {
		return userRepo.findByUsername(username);
	}
}
