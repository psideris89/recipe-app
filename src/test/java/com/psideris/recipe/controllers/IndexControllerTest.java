package com.psideris.recipe.controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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

import com.psideris.recipe.model.Recipe;
import com.psideris.recipe.services.RecipeService;

@RunWith(SpringRunner.class)
public class IndexControllerTest {

	private IndexController indexController;

	@Mock
	private RecipeService recipeService;

	@Mock
	private Model model;

	private Set<Recipe> recipes;

	private Recipe recipeA;

	private Recipe recipeB;

	private MockMvc mockMvc;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		indexController = new IndexController(recipeService);

		recipeA = new Recipe();
		recipeB = new Recipe();
		recipeB.setId(2L);

		recipes = new HashSet<>();
		mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();
	}

	@Test
	public void testMockMvc() throws Exception {
		recipes.addAll(Arrays.asList(recipeA, recipeB));
		when(recipeService.getRecipes()).thenReturn(recipes);
		mockMvc.perform(get("/")).andExpect(status().isOk())
				.andExpect(view().name("index"))
				.andExpect(model().attribute("recipes", recipes));
	}

	@Test
	public void getRecipes_shouldReturn200() throws Exception {

		recipes.addAll(Arrays.asList(recipeA, recipeB));

		when(model.addAttribute(anyString(), anySet())).thenReturn(model);
		when(recipeService.getRecipes()).thenReturn(recipes);

		ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class);

		String underTest = indexController.getIndexPage(model);

		assertEquals(underTest, "index");
		verify(recipeService, times(1)).getRecipes();
		verify(model, times(1)).addAttribute(eq("recipes"), argumentCaptor.capture());
		Set<Recipe> setInController = argumentCaptor.getValue();
		assertEquals(2, setInController.size());
	}
}
