package com.patrickKilpatrick.adminDashboard.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.patrickKilpatrick.adminDashboard.models.User;
import com.patrickKilpatrick.adminDashboard.services.UserService;
import com.patrickKilpatrick.adminDashboard.validator.UserValidator;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private UserValidator userValidator;
	
	@RequestMapping(value="/registration", method=RequestMethod.POST)
	public String registration(@Valid @ModelAttribute("user") User user, BindingResult result, Model model, HttpSession session) {
		List<User> users = userService.getUsers();
		userValidator.validate(user, result);
		if(result.hasErrors() ) {
			return "index.jsp";
		} 
		if(users.isEmpty()) {
			userService.saveWithAdminRole(user);
			model.addAttribute("registered", "You have successfully registered your account and been added as an admin! Please log in.");
			return "index.jsp";
		} else {
			userService.saveWithUserRole(user);
			model.addAttribute("registered", "You have successfully registered your account! Please log in.");
			return "index.jsp";
		}
	}
	
	@RequestMapping("login")
	public String login(@ModelAttribute("user") User user, @RequestParam(value="error", required=false) String error, @RequestParam(value="logout", required=false) String logout, Model model) {
		if(error != null) {
			model.addAttribute("errorMessage", "Invalid Credentials, Please Try Again.");
		}
		if(logout != null) {
			model.addAttribute("logoutMessage", "Logout Successful!");
		}
		return "index.jsp";
	}
	
	@RequestMapping(value = {"/", "/dashboard"})
	public String index(Principal principal, Model model) {
		String username = principal.getName();
		User user = userService.findByUsername(username);
        model.addAttribute("currentUser", user);
		return "dashboard.jsp";
	}
	
	@RequestMapping("/admin")
	public String admin(Principal principal, Model model) {
		model.addAttribute("users", userService.getUsers());
		String username = principal.getName();
        model.addAttribute("currentUser", userService.findByUsername(username));
		return "admin.jsp";
	}
	
	@RequestMapping("/delete/{id}")
	public String delete(Principal principal, @PathVariable("id") Long id) {
		userService.deleteUser(id);
		return "redirect:/admin";
	}
	
	@RequestMapping("/addAdmin/{id}")
	public String addAdmin(Principal principal, @PathVariable("id") Long id) {
		userService.addAdminRole(id);
		return "redirect:/admin";
	}
	
}
