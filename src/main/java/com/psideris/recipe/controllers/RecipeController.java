package com.psideris.recipe.controllers;

import com.psideris.recipe.commands.RecipeCommand;
import com.psideris.recipe.model.Recipe;
import com.psideris.recipe.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Slf4j
@Controller
public class RecipeController {

    private static final String REDIRECT = "redirect:/";

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService=recipeService;
    }

    @GetMapping("/recipe/{id}")
    public String getRecipe(@PathVariable final String id, final Model model) {
        log.debug("Displaying recipe");
        model.addAttribute("recipe", recipeService.getRecipeById(Long.valueOf(id)));

        return "recipe/show";
    }

    @GetMapping("/recipe/new")
    public String getRecipeForm(Model model) {
        log.debug("Displaying form to add recipe");
        model.addAttribute("recipe", new RecipeCommand());

        return "recipe/recipe-form";
    }

    @PostMapping("/recipe")
    public String addRecipe(@ModelAttribute RecipeCommand recipeCommand) {
        log.debug("Adding recipe");
        RecipeCommand savedRecipe = recipeService.saveRecipeCommand(recipeCommand);

        return REDIRECT + "recipe/" + savedRecipe.getId();
    }

    @GetMapping("/recipe/{id}/update")
    public String updateRecipe(@PathVariable String id, Model model) {
        log.debug(String.format("Updating recipe with id %s", id));
        model.addAttribute("recipe", recipeService.getRecipeCommandById(Long.valueOf(id)));

        return "recipe/recipe-form";
    }

    @GetMapping("/recipe/{id}/delete")
    public String deleteRecipe(@PathVariable final String id) {
        log.debug("Deleting recipe");
        recipeService.deleteRecipe(Long.valueOf(id));

        return REDIRECT;
    }
}
