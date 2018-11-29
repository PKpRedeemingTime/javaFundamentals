package com.patrickKilpatrick.waterBnB.controllers;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.patrickKilpatrick.waterBnB.models.Pool;
import com.patrickKilpatrick.waterBnB.models.Review;
import com.patrickKilpatrick.waterBnB.models.User;
import com.patrickKilpatrick.waterBnB.services.ReviewService;
import com.patrickKilpatrick.waterBnB.services.PoolService;
import com.patrickKilpatrick.waterBnB.services.UserService;

@Controller
@RequestMapping("/guest")
public class GuestController {
	@Autowired
	private ReviewService reviewService;
	@Autowired
	private PoolService poolService;
	@Autowired
	private UserService userService;
	
	@RequestMapping("/{id}/review")
	public String addReview(@ModelAttribute("review") Review review, Model model, @PathVariable("id") Long id) {
		Pool pool = poolService.getPool(id).orElse(null);
		model.addAttribute(pool);
		return "/guest/addReview.jsp";
	}
	
	@RequestMapping(value="/{id}/addReview", method=RequestMethod.POST)
	public String createReview(@Valid @ModelAttribute("review") Review review, @PathVariable("id") Long id, @RequestParam("pool.ratingSum") int rating, Principal principal) {
		String username = principal.getName();
		User user = userService.findByUsername(username);
		Pool pool = poolService.getPool(id).orElse(null);
		reviewService.createReview(review, pool, user, rating);
		String poolId = Long.toString(id);
		return "redirect:/show/".concat(poolId);
	}

}
