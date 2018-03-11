package com.psideris.recipe.services;

import java.util.Optional;
import java.util.Set;

import com.psideris.recipe.model.Recipe;

public interface RecipeService {

	Set<Recipe> getRecipes();

    Recipe getRecipeById(Long id);
}
