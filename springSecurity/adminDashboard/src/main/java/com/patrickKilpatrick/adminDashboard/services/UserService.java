package com.patrickKilpatrick.adminDashboard.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.patrickKilpatrick.adminDashboard.models.Role;
import com.patrickKilpatrick.adminDashboard.models.User;
import com.patrickKilpatrick.adminDashboard.repositories.RoleRepository;
import com.patrickKilpatrick.adminDashboard.repositories.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private RoleRepository roleRepo;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public void saveWithUserRole(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.addRole(roleRepo.findByName("Role_User"));
		userRepo.save(user);
	}
	
	public void saveWithAdminRole(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.addRole(roleRepo.findByName("Role_Admin"));
		user.addRole(roleRepo.findByName("Role_User"));
		userRepo.save(user);
	}
	
	public User findByUsername(String username) {
		return userRepo.findByUsername(username);
	}
	
	public void addAdminRole(Long id) {
		User user = userRepo.findById(id).orElse(null);
		user.addRole(roleRepo.findByName("Role_Admin"));
		userRepo.save(user);
	}
	
	public void deleteUser(Long id) {
		userRepo.deleteById(id);
	}
	
	public List<User> getUsers() {
		return userRepo.findAll();
	}
	
	public Role findByName(String name) {
		return roleRepo.findByName(name);
	}
}
