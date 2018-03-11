package com.psideris.recipe.controllers;

import com.psideris.recipe.services.RecipeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RecipeController {

    private static final Logger log = LoggerFactory.getLogger(RecipeController.class);

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService=recipeService;
    }

    @RequestMapping("/recipe/{id}")
    public String getRecipe(@PathVariable final String id, final Model model) {

        log.debug("Displaying recipe");
        model.addAttribute("recipe", recipeService.getRecipeById(Long.valueOf(id)));

        return "recipe/show";
    }

}
