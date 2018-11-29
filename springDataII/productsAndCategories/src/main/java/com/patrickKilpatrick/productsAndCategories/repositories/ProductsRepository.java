package com.patrickKilpatrick.productsAndCategories.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.patrickKilpatrick.productsAndCategories.models.Category;
import com.patrickKilpatrick.productsAndCategories.models.Product;

@Repository
public interface ProductsRepository extends CrudRepository<Product, Long>{
	List<Product> findByNameNotIn(List<String> name);
	Product findByNameContaining(String name);
}
