package com.patrickKilpatrick.productsAndCategories.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.patrickKilpatrick.productsAndCategories.models.Category;

@Repository
public interface CategoriesRepository extends CrudRepository<Category, Long>{
	List<Category> findByNameNotIn(List<String> name);
	Category findByNameContaining(String name);
}
