package com.patrickKilpatrick.productsAndCategories.controllers;

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

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.patrickKilpatrick.productsAndCategories.models.Category;
import com.patrickKilpatrick.productsAndCategories.models.Product;
import com.patrickKilpatrick.productsAndCategories.services.CategoriesService;
import com.patrickKilpatrick.productsAndCategories.services.ProductsService;

@Controller
@RequestMapping("/products")
public class ProductsController {
	@Autowired
	private ProductsService productsService;
	@Autowired
	private CategoriesService categoriesService;
	
	@RequestMapping("/new")
	public String newProduct(@ModelAttribute("product") Product product) {
		return "/products/newProduct.jsp";
	}
	
	@PostMapping("/new")
	public String createProduct(@Valid @ModelAttribute("product") Product product, BindingResult result) {
		if (result.hasErrors()) {
    		return "products/newProduct.jsp";
        }else{
        	Product prod = productsService.createProduct(product);
        	String prodId = Long.toString(prod.getId());
        	return "redirect:/products/".concat(prodId);
        }
	}
	
	@RequestMapping("/{id}")
	public String showProduct(Model model, @PathVariable("id") Long id) {
		Product prod = productsService.getProduct(id).orElse(null);
		List<Category> prodCat = prod.getCategories();
		model.addAttribute("product", prod);
		model.addAttribute("categories", categoriesService.availableCategories(prod));
		return "/products/showProduct.jsp";
	}
	
	@RequestMapping(value="/addCategory/{id}", method=RequestMethod.POST)
	public String addCategory(@PathVariable("id") Long id, HttpServletRequest request) {
		String name = request.getParameter("name");
		Category category = categoriesService.findByName(name);
		productsService.addCategory(category, id);
		String prodId = Long.toString(id);
		return "redirect:/products/".concat(prodId);
	}
}
