package com.psideris.recipe.controllers;

import com.psideris.recipe.model.Recipe;
import com.psideris.recipe.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
public class RecipeControllerTest {

	private RecipeController recipeController;

	@Mock
	private RecipeService recipeService;

	private Model model;

	private Recipe recipeA;

	private MockMvc mockMvc;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);

		recipeController = new RecipeController(recipeService);

		recipeA = new Recipe();

		mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();
	}

	@Test
	public void testMockMvc() throws Exception {
		when(recipeService.getRecipeById(anyLong())).thenReturn(recipeA);
		mockMvc.perform(get("/recipe/2"))
				.andExpect(status().isOk())
				.andExpect(view().name("recipe/show"))
				.andExpect(model().attribute("recipe", recipeA));
	}

}
