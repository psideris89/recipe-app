package com.psideris.recipe.controllers;

import com.psideris.recipe.services.IngredientService;
import com.psideris.recipe.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Slf4j
@Controller
public class IngredientController {

    private final RecipeService recipeService;
    private final IngredientService ingredientService;

    public IngredientController(RecipeService recipeService, IngredientService ingredientService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
    }

    @GetMapping("recipe/{id}/ingredients")
    public String getIngredients(@PathVariable String id, Model model) {
        log.debug("Retrieving ingredients");
        model.addAttribute("recipe", recipeService.getRecipeCommandById(Long.valueOf(id)));

        return "ingredient/list";
    }

    @GetMapping("recipe/{recipeId}/ingredient/{id}")
    public String getIngredient(@PathVariable String recipeId, @PathVariable String id, Model model) {

        model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(recipeId), Long
                .valueOf(id)));

        return "ingredient/show";
    }
}
