package com.patrickKilpatrick.productsAndCategories.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.patrickKilpatrick.productsAndCategories.models.Category;
import com.patrickKilpatrick.productsAndCategories.models.Product;
import com.patrickKilpatrick.productsAndCategories.services.CategoriesService;
import com.patrickKilpatrick.productsAndCategories.services.ProductsService;

@Controller
@RequestMapping("/categories")
public class CategoriesController {
	@Autowired
	private ProductsService productsService;
	@Autowired
	private CategoriesService categoriesService;
	
	@RequestMapping("/new")
	public String newCategory(@ModelAttribute("category") Category category) {
		return "/categories/newCategory.jsp";
	}
	
	@PostMapping("/new")
	public String createCategory(@Valid @ModelAttribute("category") Category category, BindingResult result) {
		if (result.hasErrors()) {
    		return "/categories/newCategory.jsp";
        }else{
        	Category cat = categoriesService.createCategory(category);
        	String catId = Long.toString(cat.getId());
        	return "redirect:/categories/".concat(catId);
        }
	}
	
	@RequestMapping("/{id}")
	public String showCategory(Model model, @PathVariable("id") Long id) {
		Category cat = categoriesService.getCategory(id).orElse(null);
		model.addAttribute("category", cat);
		model.addAttribute("products", productsService.availableProducts(cat));
		return "/categories/showCategory.jsp";
	}
	
	@RequestMapping(value="/addProduct/{id}", method=RequestMethod.POST)
	public String addProduct(@PathVariable("id") Long id, HttpServletRequest request) {
		String name = request.getParameter("name");
		Product product = productsService.findByName(name);
		categoriesService.addProduct(product, id);
		String catId = Long.toString(id);
		return "redirect:/categories/".concat(catId);
	}
	
}
