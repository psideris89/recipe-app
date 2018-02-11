package com.psideris.recipe.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.psideris.recipe.services.RecipeService;

@Controller
public class IndexController {

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	private final RecipeService recipeService;

	public IndexController(final RecipeService recipeService) {
		this.recipeService = recipeService;
	}

	@RequestMapping({ "", "/", "/index" })
	public String getRecipes(final Model model) {

		LOG.debug("Getting recipes page");
		model.addAttribute("recipes", recipeService.getRecipes());

		return "recipes";
	}

}
