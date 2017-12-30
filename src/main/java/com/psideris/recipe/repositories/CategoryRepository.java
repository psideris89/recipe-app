package com.psideris.recipe.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.psideris.recipe.model.Category;

public interface CategoryRepository extends CrudRepository<Category, Long>{

	Optional<Category> findByDescription(String description);
}
