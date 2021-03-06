package com.psideris.recipe.services;

import com.psideris.recipe.commands.RecipeCommand;
import com.psideris.recipe.model.Recipe;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipes();

    Recipe getRecipeById(Long id);

    RecipeCommand getRecipeCommandById(Long aLong);

    RecipeCommand saveRecipeCommand(RecipeCommand command);

    void deleteRecipe(Long id);
}
