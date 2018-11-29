package com.patrickKilpatrick.productsAndCategories.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patrickKilpatrick.productsAndCategories.models.Category;
import com.patrickKilpatrick.productsAndCategories.models.Product;
import com.patrickKilpatrick.productsAndCategories.repositories.ProductsRepository;

@Service
public class ProductsService {
	@Autowired
	ProductsRepository productsRepo;
	
	public Product createProduct(Product product) {
		return productsRepo.save(product);
	}
	
	public Product findByName(String name) {
		return productsRepo.findByNameContaining(name);
	}
	
	public Optional<Product> getProduct(Long id) {
		return productsRepo.findById(id);
	}
	
	public List<Product> availableProducts(Category category) {
		List<String> names = new ArrayList<String>();
		List<Product> currentProds = category.getProducts();
		if(currentProds.isEmpty()) {
			names.add("");
		} else {
			for(Product p : currentProds) {
				names.add(p.getName());
			}
		}
		List<Product> prods = productsRepo.findByNameNotIn(names);
		return prods;
	}
	
	public void addCategory(Category cat, Long id) {
		Product prod = productsRepo.findById(id).orElse(null);
		prod.addCategory(cat);
		productsRepo.save(prod);
	}
	
}
