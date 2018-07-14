package com.psideris.recipe.controllers;

import com.psideris.recipe.services.RecipeService;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final RecipeService recipeService;

    public IndexController(final RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping({"", "/", "/index"})
    public String getIndexPage(Model model) {
        log.debug("Getting Index page");

        model.addAttribute("recipes", recipeService.getRecipes());

        return "index";
    }

}
