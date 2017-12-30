package com.psideris.recipe.bootstrap;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.psideris.recipe.model.Category;
import com.psideris.recipe.model.Difficulty;
import com.psideris.recipe.model.Ingredient;
import com.psideris.recipe.model.Notes;
import com.psideris.recipe.model.Recipe;
import com.psideris.recipe.model.UnitOfMeasure;
import com.psideris.recipe.repositories.CategoryRepository;
import com.psideris.recipe.repositories.RecipeRepository;
import com.psideris.recipe.repositories.UnitOfMeasureRepository;

@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {

	private UnitOfMeasureRepository unitOfMeasureRepository;
	private CategoryRepository categoryRepository;
	private RecipeRepository recipeRepository;

	List<Recipe> recipes = new ArrayList<>();

	public RecipeBootstrap(UnitOfMeasureRepository unitOfMeasureRepository, CategoryRepository categoryRepository,
			RecipeRepository recipeRepository) {
		super();
		this.unitOfMeasureRepository = unitOfMeasureRepository;
		this.categoryRepository = categoryRepository;
		this.recipeRepository = recipeRepository;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		recipeRepository.saveAll(getRecipes());
	}

	public List<Recipe> getRecipes() {

		Optional<UnitOfMeasure> teaspoonUomOptional = unitOfMeasureRepository.findByDescription("Teaspoon");
		Optional<UnitOfMeasure> tablespoonUomOptional = unitOfMeasureRepository.findByDescription("Tablespoon");
		Optional<UnitOfMeasure> cupUomOptional = unitOfMeasureRepository.findByDescription("Cup");
		Optional<UnitOfMeasure> pinchUomOptional = unitOfMeasureRepository.findByDescription("Pinch");
		Optional<UnitOfMeasure> ounceUomOptional = unitOfMeasureRepository.findByDescription("Ounce");
		Optional<UnitOfMeasure> dashUomOptional = unitOfMeasureRepository.findByDescription("Dash");
		Optional<UnitOfMeasure> eachUomOptional = unitOfMeasureRepository.findByDescription("Each");

		validateOptionals(Arrays.asList(teaspoonUomOptional, tablespoonUomOptional, cupUomOptional, pinchUomOptional,
				ounceUomOptional, dashUomOptional, eachUomOptional), UnitOfMeasure.class);

		Optional<Category> americanCategoryOptional = categoryRepository.findByDescription("American");
		Optional<Category> italianCategoryOptional = categoryRepository.findByDescription("Italian");
		Optional<Category> mexicanCategoryOptional = categoryRepository.findByDescription("Mexican");
		Optional<Category> fastFoodCategoryOptional = categoryRepository.findByDescription("Fast Food");
		Optional<Category> greekCategoryOptional = categoryRepository.findByDescription("Greek");
		Optional<Category> chineseCategoryOptional = categoryRepository.findByDescription("Chinese");
		Optional<Category> indianCategoryOptional = categoryRepository.findByDescription("Indian");

		validateOptionals(Arrays.asList(americanCategoryOptional, italianCategoryOptional, mexicanCategoryOptional,
				fastFoodCategoryOptional, greekCategoryOptional, chineseCategoryOptional, indianCategoryOptional),
				Category.class);

		System.out.println("Validation Success");

		Recipe r1 = new Recipe();
		r1.setDescription("How to Make Perfect Guacamole");
		r1.setPrepTime(10);
		r1.setCookTime(0);
		r1.setDifficulty(Difficulty.EASY);
		r1.setSource("simply recipes");
		r1.setUrl("http://www.simplyrecipes.com/recipes/perfect_guacamole/");
		r1.setDirections(
				"1 Cut avocado, remove flesh: Cut the avocados in half. Remove seed. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl.\n"
						+ "\n"
						+ "2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)\n"
						+ "\n"
						+ "3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n"
						+ "\n"
						+ "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n"
						+ "\n"
						+ "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n"
						+ "\n"
						+ "4 Cover with plastic and chill to store: Place plastic wrap on the surface of the guacamole cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.\n"
						+ "\n"
						+ "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.");

		Notes n1 = new Notes();
		n1.setRecipeNotes(
				"For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados.\n"
						+ "\n"
						+ "Feel free to experiment! One classic Mexican guacamole has pomegranate seeds and chunks of peaches in it (a Diana Kennedy favorite). Try guacamole with added pineapple, mango, or strawberries (see our Strawberry Guacamole).\n"
						+ "\n"
						+ "The simplest version of guacamole is just mashed avocados with salt. Don't let the lack of availability of other ingredients stop you from making guacamole.\n"
						+ "\n"
						+ "To extend a limited supply of avocados, add either sour cream or cottage cheese to your guacamole dip. Purists may be horrified, but so what? It tastes great.");

		/*
		 * TODO
		 */
		r1.setNotes(n1);
		n1.setRecipe(r1);
		
		r1.getIngredients().add(new Ingredient("rice avocados", BigDecimal.valueOf(2), eachUomOptional.get()));
		r1.getIngredients().add(new Ingredient("Kosher salt", BigDecimal.valueOf(0.5), teaspoonUomOptional.get()));
		r1.getIngredients().add(
				new Ingredient("fresh lime juice or lemon juice", BigDecimal.valueOf(1), tablespoonUomOptional.get()));
		r1.getIngredients().add(new Ingredient("minced red onion or thinly sliced green onion", BigDecimal.valueOf(2),
				tablespoonUomOptional.get()));
		r1.getIngredients().add(new Ingredient("serrano chiles, stems and seeds removed, minced", BigDecimal.valueOf(2),
				eachUomOptional.get()));
		r1.getIngredients().add(new Ingredient("cilantro (leaves and tender stems), finely chopped",
				BigDecimal.valueOf(2), tablespoonUomOptional.get()));
		r1.getIngredients()
				.add(new Ingredient("freshly grated black pepper", BigDecimal.valueOf(1), dashUomOptional.get()));
		r1.getIngredients().add(new Ingredient("ripe tomato, seeds and pulp removed, chopped", BigDecimal.valueOf(0.5),
				eachUomOptional.get()));

		r1.getCategories().addAll(Arrays.asList(americanCategoryOptional.get(), mexicanCategoryOptional.get()));

		Recipe r2 = new Recipe();
		r2.setDescription("Spicy Grilled Chicken Taco");
		r2.setCookTime(9);
		r2.setPrepTime(20);
		r2.setDifficulty(Difficulty.MODERATE);

		r2.setDirections("1 Prepare a gas or charcoal grill for medium-high, direct heat.\n"
				+ "2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\n"
				+ "Set aside to marinate while the grill heats and you prepare the rest of the toppings.\n" + "\n"
				+ "\n"
				+ "3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.\n"
				+ "4 Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.\n"
				+ "Wrap warmed tortillas in a tea towel to keep them warm until serving.\n"
				+ "5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges.\n"
				+ "\n" + "\n"
				+ "Read more: http://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/#ixzz4jvtrAnNm");

		Notes n2 = new Notes();
		n2.setRecipeNotes("We have a family motto and it is this: Everything goes better in a tortilla.\n"
				+ "Any and every kind of leftover can go inside a warm tortilla, usually with a healthy dose of pickled jalapenos. I can always sniff out a late-night snacker when the aroma of tortillas heating in a hot pan on the stove comes wafting through the house.\n"
				+ "Today’s tacos are more purposeful – a deliberate meal instead of a secretive midnight snack!\n"
				+ "First, I marinate the chicken briefly in a spicy paste of ancho chile powder, oregano, cumin, and sweet orange juice while the grill is heating. You can also use this time to prepare the taco toppings.\n"
				+ "Grill the chicken, then let it rest while you warm the tortillas. Now you are ready to assemble the tacos and dig in. The whole meal comes together in about 30 minutes!\n"
				+ "\n" + "\n"
				+ "Read more: http://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/#ixzz4jvu7Q0MJ");
		n2.setRecipe(r2);
		r2.setNotes(n2);

		r2.getIngredients()
				.add(new Ingredient("Ancho Chili Powder", new BigDecimal(2), tablespoonUomOptional.get(), r2));
		r2.getIngredients().add(new Ingredient("Dried Oregano", new BigDecimal(1), teaspoonUomOptional.get(), r2));
		r2.getIngredients().add(new Ingredient("Dried Cumin", new BigDecimal(1), teaspoonUomOptional.get(), r2));
		r2.getIngredients().add(new Ingredient("Sugar", new BigDecimal(1), teaspoonUomOptional.get(), r2));
		r2.getIngredients().add(new Ingredient("Salt", new BigDecimal(".5"), teaspoonUomOptional.get(), r2));
		r2.getIngredients()
				.add(new Ingredient("Clove of Garlic, Choppedr", new BigDecimal(1), eachUomOptional.get(), r2));
		r2.getIngredients()
				.add(new Ingredient("finely grated orange zestr", new BigDecimal(1), tablespoonUomOptional.get(), r2));
		r2.getIngredients()
				.add(new Ingredient("fresh-squeezed orange juice", new BigDecimal(3), tablespoonUomOptional.get(), r2));
		r2.getIngredients().add(new Ingredient("Olive Oil", new BigDecimal(2), tablespoonUomOptional.get(), r2));
		r2.getIngredients()
				.add(new Ingredient("boneless chicken thighs", new BigDecimal(4), tablespoonUomOptional.get(), r2));
		r2.getIngredients().add(new Ingredient("small corn tortillasr", new BigDecimal(8), eachUomOptional.get(), r2));
		r2.getIngredients().add(new Ingredient("packed baby arugula", new BigDecimal(3), cupUomOptional.get(), r2));
		r2.getIngredients()
				.add(new Ingredient("medium ripe avocados, slic", new BigDecimal(2), eachUomOptional.get(), r2));
		r2.getIngredients()
				.add(new Ingredient("radishes, thinly sliced", new BigDecimal(4), eachUomOptional.get(), r2));
		r2.getIngredients()
				.add(new Ingredient("cherry tomatoes, halved", new BigDecimal(".5"), pinchUomOptional.get(), r2));
		r2.getIngredients()
				.add(new Ingredient("red onion, thinly sliced", new BigDecimal(".25"), eachUomOptional.get(), r2));
		r2.getIngredients()
				.add(new Ingredient("Roughly chopped cilantro", new BigDecimal(4), eachUomOptional.get(), r2));
		r2.getIngredients().add(new Ingredient("cup sour cream thinned with 1/4 cup milk", new BigDecimal(4),
				cupUomOptional.get(), r2));
		r2.getIngredients().add(new Ingredient("lime, cut into wedges", new BigDecimal(4), eachUomOptional.get(), r2));

		r2.getCategories().addAll(Arrays.asList(americanCategoryOptional.get(), mexicanCategoryOptional.get()));

		recipes.addAll(Arrays.asList(r1, r2));

		return recipes;

	}

	private void validateOptionals(List<Optional<?>> optionals, Class<?> cl) {

		optionals.stream().forEach(o -> {
			if (!o.isPresent()) {
				throw new RuntimeException("Missing: " + cl.getName());
			}
		});
	}
}
