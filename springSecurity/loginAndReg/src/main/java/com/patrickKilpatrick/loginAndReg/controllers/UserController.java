package com.patrickKilpatrick.loginAndReg.controllers;

import java.security.Principal;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.patrickKilpatrick.loginAndReg.models.User;
import com.patrickKilpatrick.loginAndReg.services.UserService;
import com.patrickKilpatrick.loginAndReg.validator.UserValidator;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private UserValidator userValidator;
	
	@RequestMapping("/loginRegistration")
	public String registerForm(@Valid @ModelAttribute("user") User user) {
		return "loginRegistration.jsp";
	}
	
	@RequestMapping(value="/registration", method=RequestMethod.POST)
	public String registration(@Valid @ModelAttribute("user") User user, BindingResult result, Model model, HttpSession session) {
		userValidator.validate(user, result);
		if(result.hasErrors() ) {
			return "loginRegistration.jsp";
		} else {
			userService.addUser(user);
			model.addAttribute("welcome", "Your account has been successfully created! Please login.");
			return "redirect:/loginRegistration";
		}
	}
	
	@RequestMapping("login")
	public String login(@ModelAttribute("user") User user, @RequestParam(value="error", required=false) String error, @RequestParam(value="logout", required=false) String logout, Model model) {
		if(error != null) {
			model.addAttribute("errorMessage", "Invalid Credentials, Please Try Again.");
			return "loginRegistration.jsp";
		}
		if(logout != null) {
			model.addAttribute("logoutMessage", "Logout Successful!");
			return "loginRegistration.jsp";
		}
		return "redirect:/";
	}
	
	@RequestMapping("/logout")
	public String logout() {
		return "redirect:/loginRegistration";
	}
	
	@RequestMapping(value= {"/", "/home"})
	public String home(Principal principal, Model model) {
		String username = principal.getName();
		model.addAttribute("currentUser", userService.findByUsername(username));
		return "home.jsp";
	}

}
