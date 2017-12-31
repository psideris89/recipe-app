package com.psideris.recipe.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.psideris.recipe.services.RecipeService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class IndexController {

	private final RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"", "/", "/index"})
    public String getIndexPage() {

    	log.debug("Getting index page");
        return "index";
    }
	
	@GetMapping("/recipes")
	public String getRecipes(Model model) {
	
		log.debug("Getting recipes page");
		model.addAttribute("recipes", recipeService.getRecipes());
		return "recipes";
	}
}
