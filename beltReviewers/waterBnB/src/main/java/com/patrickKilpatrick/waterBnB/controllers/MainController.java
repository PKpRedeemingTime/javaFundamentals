package com.patrickKilpatrick.waterBnB.controllers;

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

import com.patrickKilpatrick.waterBnB.models.Pool;
import com.patrickKilpatrick.waterBnB.models.Review;
import com.patrickKilpatrick.waterBnB.models.User;
import com.patrickKilpatrick.waterBnB.services.PoolService;
import com.patrickKilpatrick.waterBnB.services.UserService;
import com.patrickKilpatrick.waterBnB.validators.UserValidator;

@Controller
public class MainController {
	@Autowired
	private UserService userService;
	@Autowired
	private PoolService poolService;
	@Autowired
	private UserValidator userValidator;
	
	@RequestMapping("/")
	public String index(Principal principal, Model model) {
		if(principal != null) {
			String username = principal.getName();
			User user = userService.findByUsername(username);
			model.addAttribute("currentUser", user);
		}
		return "/main/index.jsp";
	}
	
	@RequestMapping("/loginReg")
	public String loginReg(@ModelAttribute("user") User user) {
		return "/main/loginReg.jsp";
	}
	
	@RequestMapping(value="/registration", method=RequestMethod.POST)
	public String registration(@Valid @ModelAttribute("user") User user, BindingResult result, Model model, HttpSession session) {
		userValidator.validate(user, result);
		if(result.hasErrors() ) {
			return "/main/loginReg.jsp";
		} else {
			if(user.getRole().equals("host")) {
				userService.createHostUser(user);
				model.addAttribute("registered", "You have successfully registered as a host! Please log in.");
				return "/main/loginReg.jsp";
			} else {
				userService.createGuestUser(user);
				model.addAttribute("registered", "You have successfully registered as a guest! Please log in.");
				return "/main/loginReg.jsp";
			}
		}
	}
	
	@RequestMapping("/login")
	public String login(@ModelAttribute("user") User user, @RequestParam(value="error", required=false) String error, @RequestParam(value="logout", required=false) String logout, Model model) {
		if(error != null) {
			model.addAttribute("errorMessage", "Invalid Credentials, Please Try Again.");
		}
		if(logout != null) {
			model.addAttribute("logoutMessage", "Logout Successful!");
		}
		return "/main/index.jsp";
	}
	
	@RequestMapping(value="/search", method=RequestMethod.POST)
	public String poolSearch(@RequestParam("address") String address, Model model, Principal principal) {
		if(principal != null) {
			String username = principal.getName();
			User user = userService.findByUsername(username);
			model.addAttribute("currentUser", user);
		}
		List<Pool> pools = poolService.findByAddress(address);
		model.addAttribute("pools", pools);
		return "/main/searchResults.jsp";
	}
	
	@RequestMapping("/show/{id}")
	public String showPool(Principal principal, Model model, @PathVariable("id") Long id) {
		if(principal != null) {
			String username = principal.getName();
			User user = userService.findByUsername(username);
			model.addAttribute("currentUser", user);
		}
		Pool pool = poolService.getPool(id).orElse(null);
		List<Review> reviews = poolService.getReviews(id);
		model.addAttribute("reviews", reviews);
		model.addAttribute("pool", pool);
		return "/main/showPool.jsp";
	}

}
