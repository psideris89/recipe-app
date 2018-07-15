package com.psideris.recipe.services;

import com.psideris.recipe.commands.IngredientCommand;
import com.psideris.recipe.converters.IngredientToIngredientCommand;
import com.psideris.recipe.model.Recipe;
import com.psideris.recipe.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.lang.String.format;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {

    private IngredientToIngredientCommand ingredientToIngredientCommand;
    private RecipeRepository recipeRepository;

    public IngredientServiceImpl(IngredientToIngredientCommand ingredientToIngredientCommand,
                                 RecipeRepository recipeRepository) {
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.recipeRepository = recipeRepository;
    }

    @Override
    public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

        if (!recipeOptional.isPresent()) {
            log.error(format("Failed to retrieve recipe with id %s", recipeId));
            throw new RuntimeException();
        }

        Optional<IngredientCommand> ingredientCommandOptional = recipeOptional.get()
                .getIngredients().stream()
                .filter(s -> s.getId().equals(ingredientId))
                .map(s -> ingredientToIngredientCommand.convert(s))
                .findFirst();

        if (!ingredientCommandOptional.isPresent()) {
            log.error(format("Failed to retrieve ingredient with id %s", ingredientId));
            throw new RuntimeException();
        }

        return ingredientCommandOptional.get();
    }
}
