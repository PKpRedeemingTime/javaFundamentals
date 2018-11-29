package com.patrickKilpatrick.productsAndCategories.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patrickKilpatrick.productsAndCategories.models.Category;
import com.patrickKilpatrick.productsAndCategories.models.Product;
import com.patrickKilpatrick.productsAndCategories.repositories.CategoriesRepository;

@Service
public class CategoriesService {
	@Autowired
	CategoriesRepository categoriesRepo;
	
	public Category createCategory(Category category) {
		return categoriesRepo.save(category);
	}
	
	public Category findByName(String name) {
		return categoriesRepo.findByNameContaining(name);
	}
	
	public Optional<Category> getCategory(Long id) {
		return categoriesRepo.findById(id);
	}
	
	public List<Category> availableCategories(Product product) {
		List<String> names = new ArrayList<String>();
		List<Category> currentCats = product.getCategories();
		if(currentCats.isEmpty()) {
			names.add("");
		} else {
			for(Category c : currentCats) {
				names.add(c.getName());
			}
		}
		List<Category> cats = categoriesRepo.findByNameNotIn(names);
		return cats;
	}
	
	public void addProduct(Product prod, Long id) {
		Category cat = categoriesRepo.findById(id).orElse(null);
		cat.addProduct(prod);
		categoriesRepo.save(cat);
	}
}
