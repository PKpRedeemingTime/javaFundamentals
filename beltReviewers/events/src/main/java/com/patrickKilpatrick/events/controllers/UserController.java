package com.patrickKilpatrick.events.controllers;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.patrickKilpatrick.events.models.Event;
import com.patrickKilpatrick.events.models.Message;
import com.patrickKilpatrick.events.models.User;
import com.patrickKilpatrick.events.services.EventService;
import com.patrickKilpatrick.events.services.MessageService;
import com.patrickKilpatrick.events.services.UserService;
import com.patrickKilpatrick.events.validators.UserValidator;

@Controller
public class UserController {
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
	@Autowired
	private UserService userService;
	@Autowired
	private EventService eventService;
	@Autowired
	private MessageService messageService;
	@Autowired
	private UserValidator userValidator;
	
	@RequestMapping(value="/registration", method=RequestMethod.POST)
	public String registration(@Valid @ModelAttribute("user") User user, BindingResult result, Model model, HttpSession session) {
		userValidator.validate(user, result);
		if(result.hasErrors() ) {
			return "index.jsp";
		} else {
			userService.createUser(user);
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
	
	@RequestMapping(value = {"/", "/events"})
	public String index(@ModelAttribute("event") Event event, Principal principal, Model model) {
		String username = principal.getName();
		User user = userService.findByUsername(username);
        model.addAttribute("currentUser", user);
        List<Event> events = eventService.getAll();
        model.addAttribute("events", events);
		return "eventsDashboard.jsp";
	}
	
	@RequestMapping(value="/events", method=RequestMethod.POST)
	public String createEvent(@Valid @ModelAttribute("event") Event event, BindingResult result, Principal principal, Model model) {
		if(result.hasErrors() ) {
			String username = principal.getName();
			User user = userService.findByUsername(username);
	        model.addAttribute("currentUser", user);
	        List<Event> events = eventService.getAll();
	        model.addAttribute("events", events);
			return "eventsDashboard.jsp";
		} else {
			eventService.createEvent(event);
			String owner = principal.getName();
			User user = userService.findByUsername(owner);
			userService.addEvent(user, event);
			String id = Long.toString(event.getId());
			return "redirect:/events/".concat(id);
		}
	}
	
	@RequestMapping(value="events/{id}")
	public String showEvent(@ModelAttribute("message") Message message, @PathVariable("id") Long id, Model model, Principal principal) {
		Event event = eventService.getEvent(id).orElse(null);
		String owner = event.getOwner();
		User eventOwner = userService.findByUsername(owner);
		String username = principal.getName();
		User currentUser = userService.findByUsername(username);
		List<Message> messages = eventService.getMessages(id);
		model.addAttribute("owner", eventOwner);
		model.addAttribute("event", event);
		model.addAttribute("currentUser", currentUser);
		model.addAttribute("messages", messages);
		return "showEvent.jsp";
	}
	
	@RequestMapping(value="/events/{id}", method=RequestMethod.POST)
	public String createMessage(@Valid @ModelAttribute("message") Message message, BindingResult result, Principal principal, @PathVariable("id") Long id, Model model) {
		if(result.hasErrors() ) {
			String eventId = Long.toString(id);
			System.out.println(result);
			return "redirect:/events/".concat(eventId);
		} else {
			Event currentEvent = eventService.getEvent(id).orElse(null);
			model.addAttribute("event", currentEvent);
			String username = principal.getName();
			User currentUser = userService.findByUsername(username);
			String eventId = Long.toString(id);
			messageService.createMessage(message, currentEvent, currentUser);
			return "redirect:/events/".concat(eventId);
		}
	}
	
	@RequestMapping(value="/events/{id}/edit")
	public String editEvent(@ModelAttribute("event") Event event, Model model, @PathVariable("id") Long id, Principal principal) {
		String username = principal.getName();
		User currentUser = userService.findByUsername(username);
		Event currentEvent = eventService.getEvent(id).orElse(null);
		model.addAttribute("user", currentUser);
		model.addAttribute("event", currentEvent);
		return "editEvent.jsp";
	}
	
	@RequestMapping(value="/events/{id}/edit", method=RequestMethod.POST)
	public String updateEvent(@Valid @ModelAttribute("event") Event event, BindingResult result, @PathVariable("id") Long id) {
		eventService.updateEvent(event);
		String eventId = Long.toString(event.getId());
		return "redirect:/events/".concat(eventId);
	}
	
	@RequestMapping(value="/events/{id}/delete")
	public String deleteEvent(@PathVariable("id") Long id) {
		eventService.deleteEvent(id);
		return "redirect:/";
	}
	
	@RequestMapping(value="events/{id}/join")
	public String joinEvent(@PathVariable("id") Long id, Principal principal) {
		Event event = eventService.getEvent(id).orElse(null);
		String currentUser = principal.getName();
		User user = userService.findByUsername(currentUser);
		userService.addEvent(user, event);
		return "redirect:/";
	}
	
	@RequestMapping(value="events/{id}/leave")
	public String leaveEvent(@PathVariable("id") Long id, Principal principal) {
		Event event = eventService.getEvent(id).orElse(null);
		String currentUser = principal.getName();
		User user = userService.findByUsername(currentUser);
		userService.leaveEvent(user, event);
		return "redirect:/";
	}

}
