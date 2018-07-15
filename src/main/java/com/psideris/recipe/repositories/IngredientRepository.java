package com.psideris.recipe.repositories;

import com.psideris.recipe.model.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient, Long> {
}
