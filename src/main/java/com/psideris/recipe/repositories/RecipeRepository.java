package com.psideris.recipe.repositories;

import org.springframework.data.repository.CrudRepository;

import com.psideris.recipe.model.Recipe;

public interface RecipeRepository extends CrudRepository<Recipe, Long>{

}
