package com.patrickKilpatrick.waterBnB.controllers;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.patrickKilpatrick.waterBnB.models.Pool;
import com.patrickKilpatrick.waterBnB.models.User;
import com.patrickKilpatrick.waterBnB.services.PoolService;
import com.patrickKilpatrick.waterBnB.services.UserService;

@Controller
@RequestMapping("/host")
public class HostController {
	@Autowired
	private UserService userService;
	@Autowired
	private PoolService poolService;
	
	@RequestMapping("/dashboard")
	public String hostDashboard(@ModelAttribute("pool") Pool pool, Principal principal, Model model) {
		String username = principal.getName();
		User user = userService.findByUsername(username);
		model.addAttribute("currentUser", user);
		return "/host/hostDashboard.jsp";
	}
	
	@RequestMapping(value="/pool", method=RequestMethod.POST)
	public String createPool(@Valid @ModelAttribute("pool") Pool pool, BindingResult result, Principal principal) {
		String username = principal.getName();
		User user = userService.findByUsername(username);
		poolService.createPool(pool, user);
		return "redirect:/host/dashboard";
	}
	
	@RequestMapping("/pool/{id}")
	public String showEditPool(@ModelAttribute("pool") Pool pool, @PathVariable("id") Long id, Model model) {
		Pool currentPool = poolService.getPool(id).orElse(null);
		model.addAttribute("pool", currentPool);
		return "/host/hostShowEditPool.jsp";
	}
	
	@RequestMapping(value="/pool/{id}/edit", method=RequestMethod.POST)
	public String updatePool(@Valid @ModelAttribute("pool") Pool pool, BindingResult result, Principal principal) {
		String username = principal.getName();
		User user = userService.findByUsername(username);
		poolService.updatePool(pool, user);
		return "redirect:/host/dashboard";
	}

}
