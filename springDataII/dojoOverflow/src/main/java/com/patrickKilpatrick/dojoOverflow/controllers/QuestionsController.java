package com.patrickKilpatrick.dojoOverflow.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
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

import com.patrickKilpatrick.dojoOverflow.models.Answer;
import com.patrickKilpatrick.dojoOverflow.models.Question;
import com.patrickKilpatrick.dojoOverflow.models.Tag;
import com.patrickKilpatrick.dojoOverflow.services.AnswerService;
import com.patrickKilpatrick.dojoOverflow.services.QuestionService;
import com.patrickKilpatrick.dojoOverflow.services.TagService;

@Controller
public class QuestionsController {
	@Autowired
	private QuestionService questionService;
	@Autowired
	private AnswerService answerService;
	@Autowired
	private TagService tagService;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(List.class, "tags", new CustomCollectionEditor(List.class) {                
	                  
	        public void setAsText(String element) {
	        	// information coming from the form
	        	String[] listOfTagString = element.split(", ");
	        	List<Tag> listOfTags = new ArrayList<Tag>();
	        	
	        	int maxLength = listOfTagString.length > 2 ? 3 : listOfTagString.length;
	        	
	        	for(int i = 0; i < maxLength; i++) {
	        		listOfTags.add(tagService.findOrCreateTag(listOfTagString[i]));
	        	}
	        	
	        	// set the value of the element to be a list of tags
	        	setValue(listOfTags);
	        }
        }); 
	}
	
	@RequestMapping("/")
	public String index(Model model) {
		model.addAttribute("questions", questionService.getAll());
		return "/questions/index.jsp";
	}
	
	@RequestMapping("/new")
	public String newQuestion(@ModelAttribute("questionModel") Question question) {
		return "/questions/newQuestion.jsp";
	}
	
	@RequestMapping(value="/createQuestion", method=RequestMethod.POST)
	public String createQuestion(@Valid @ModelAttribute("questionModel") Question question, BindingResult result, @RequestParam("tags") String tags) {
		if (result.hasErrors()) {
    		return "/questions/newQuestion.jsp";
        } else {
        	questionService.createQuestion(question);
        	return "redirect:/";
        }
	}
	
	@RequestMapping("/{id}")
	public String showQuestion(@ModelAttribute("answerModel") Answer answer, Model model, @PathVariable("id") Long id) {
		Question question = questionService.getQuestion(id).orElse(null);
		model.addAttribute("question", question);
		return "/questions/answer.jsp";
	}
	
	@RequestMapping(value="/createAnswer/{id}", method=RequestMethod.POST)
	public String createAnswer(@Valid @ModelAttribute("answerModel") Answer answer, BindingResult result, @PathVariable("id") Long id, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("question", questionService.getQuestion(id).orElse(null));
    		return "/questions/answer.jsp";
        } else {
        	answerService.createAnswer(answer);
        	String questionId = Long.toString(id);
        	return "redirect:/".concat(questionId);
        }
	}

}
