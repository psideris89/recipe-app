package com.psideris.recipe.services;

import com.psideris.recipe.commands.IngredientCommand;

public interface IngredientService {

    IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long IngredientId);
}
