package com.psideris.recipe.services;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.psideris.recipe.model.Recipe;
import com.psideris.recipe.repositories.RecipeRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    private final RecipeRepository recipeRepository;

    public RecipeServiceImpl(final RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Set<Recipe> getRecipes() {
        LOG.debug("Getting recipes from RecipeServiceImpl");
        Set<Recipe> recipeSet = new HashSet<>();

        recipeRepository.findAll().iterator().forEachRemaining(recipeSet::add);

        return recipeSet;
    }

    @Override
    public Recipe getRecipeById(Long id) {
        LOG.debug(String.format("Getting recipe with id %s", id));
        Optional<Recipe> recipeOptional = recipeRepository.findById(id);

        recipeOptional.orElseThrow(() -> new RuntimeException("Recipe not Found!"));

        return recipeOptional.get();
    }

}
