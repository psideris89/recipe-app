package com.psideris.recipe.services;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import com.psideris.recipe.commands.IngredientCommand;
import com.psideris.recipe.commands.RecipeCommand;
import com.psideris.recipe.converters.RecipeCommandToRecipe;
import com.psideris.recipe.converters.RecipeToRecipeCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.psideris.recipe.model.Recipe;
import com.psideris.recipe.repositories.RecipeRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import static java.lang.String.format;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final RecipeCommandToRecipe recipeCommandToRecipe;
    private final RecipeToRecipeCommand recipeToRecipeCommand;

    public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeCommandToRecipe recipeCommandToRecipe, RecipeToRecipeCommand recipeToRecipeCommand) {
        this.recipeRepository = recipeRepository;
        this.recipeCommandToRecipe = recipeCommandToRecipe;
        this.recipeToRecipeCommand = recipeToRecipeCommand;
    }

    @Override
    public Set<Recipe> getRecipes() {
        log.debug("Getting recipes from RecipeServiceImpl");
        Set<Recipe> recipeSet = new HashSet<>();

        recipeRepository.findAll().iterator().forEachRemaining(recipeSet::add);

        return recipeSet;
    }

    @Override
    public Recipe getRecipeById(Long id) {
        log.debug(format("Getting recipe with id %s", id));
        Optional<Recipe> recipeOptional = recipeRepository.findById(id);

        recipeOptional.orElseThrow(() -> new RuntimeException("Recipe not Found!"));

        return recipeOptional.get();
    }

    @Override
    @Transactional
    public RecipeCommand getRecipeCommandById(Long id) {
        return recipeToRecipeCommand.convert(getRecipeById(id));
    }

    @Override
    @Transactional
    public void deleteRecipe(Long id) {
        log.debug(format("Deleting recipe with id %s", id));
        recipeRepository.deleteById(id);
    }

    @Override
    @Transactional
    public RecipeCommand saveRecipeCommand(RecipeCommand command) {
        Recipe convertedRecipe = recipeCommandToRecipe.convert(command);
        Recipe savedRecipe = recipeRepository.save(convertedRecipe);
        log.debug("Saved RecipeId:" + savedRecipe.getId());

        return recipeToRecipeCommand.convert(savedRecipe);
    }
}
