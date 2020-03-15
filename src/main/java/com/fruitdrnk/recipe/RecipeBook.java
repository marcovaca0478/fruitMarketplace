package com.fruitdrnk.recipe;

import java.util.List;

import com.fruitdrnk.inventory.Inventory;
import com.fruitdrnk.product.Ingredient;

/**
 * Book of Recipes for the Fruit Drink marketplace. Simple (hard coded)
 * implementation of the following rules:
 * 
 * For every 100ml of blended fruit drink, her recipe requires 50ml of blended
 * fruit 30ml of ice 20ml of condensed milk 8g of sugar
 * 
 * The vendor offers 3 flavors of fruit drink Strawberry: Requires 100g of
 * strawberries for each 100ml of blended strawberries Banana: Requires 120g of
 * bananas for each 100ml of blended bananas Mango: Requires 140g of mango for
 * each 100ml of blended mangoes
 * 
 * @author Marco Vaca
 */
public class RecipeBook {

	private static final int STRAWBERRY_PORTION = 50;
	private static final int BANANA_PORTION = 60;
	private static final int MANGO_PORTION = 70;
	private static final int ICE_PORTION = 30;
	private static final int COND_MILK_PORTION = 20;
	private static final int SUGAR_PORTION = 8;

	private Inventory inventory;

	/**
	 * Default constructor
	 * 
	 * @param i
	 */
	public RecipeBook(Inventory i) {
		this.inventory = i;
	}

	/**
	 * Return a Strawberry drink recipe
	 * 
	 * @param sizeOfDrink
	 * @return
	 */
	public Recipe getStrawberryDrinkRecipe(int sizeOfDrink) {
		// Put Strawberry blend
		return getDrinkRecipe(inventory.getIngredient("i01"), STRAWBERRY_PORTION, sizeOfDrink);
	}

	/**
	 * Return a Banana drink recipe
	 * 
	 * @param sizeOfDrink
	 * @return
	 */
	public Recipe getBananaDrinkRecipe(int sizeOfDrink) {
		// Put Banana blend
		return getDrinkRecipe(inventory.getIngredient("i02"), BANANA_PORTION, sizeOfDrink);
	}

	/**
	 * Return a Mango drink recipe
	 * 
	 * @param sizeOfDrink
	 * @return
	 */
	public Recipe getMangoDrinkRecipe(int sizeOfDrink) {
		// Put Mango blend
		return getDrinkRecipe(inventory.getIngredient("i03"), MANGO_PORTION, sizeOfDrink);
	}

	/**
	 * Return a Mixed drink recipe
	 * 
	 * @param fruitCodes
	 * @param sizeOfDrink
	 * @return
	 */
	public Recipe getMixedDrinkRecipe(List<String> fruitCodes, int sizeOfDrink) {
		Recipe recipe = Recipe.generateNewRecipe();

		int factorToSplit = fruitCodes.size();

		// Iterate on the fruit ingredients
		for (String code : fruitCodes) {
			Ingredient fruitIngredient = inventory.getIngredient(code);

			switch (code) {
			case ("i01"):
				// Add the fruit
				recipe.addIngredient(fruitIngredient, sizeOfDrink * STRAWBERRY_PORTION / factorToSplit);
				break;
			case ("i02"):
				// Add the fruit
				recipe.addIngredient(fruitIngredient, sizeOfDrink * BANANA_PORTION / factorToSplit);
				break;
			case ("i03"):
				// Add the fruit
				recipe.addIngredient(fruitIngredient, sizeOfDrink * BANANA_PORTION / factorToSplit);
				break;
			default:
				System.out.println("Invalid code detected");
				continue;
			}
		}

		// Add the common ingredients
		addCommonIngredients(recipe, sizeOfDrink);

		return recipe;
	}

	// Generic method used to prepare Drink recipes
	private Recipe getDrinkRecipe(Ingredient fruitIngredient, int fruitQuantity, int sizeOfDrink) {
		Recipe recipe = Recipe.generateNewRecipe();

		// Add the fruit
		recipe.addIngredient(fruitIngredient, sizeOfDrink * fruitQuantity);

		// Add the common ingredients
		addCommonIngredients(recipe, sizeOfDrink);

		return recipe;
	}

	// Add common ingredients for the drink
	private void addCommonIngredients(Recipe recipe, int sizeOfDrink) {
		// 30 ml Ice
		recipe.addIngredient(inventory.getIngredient("i04"), sizeOfDrink * ICE_PORTION);
		// 20 ml Condensed milk
		recipe.addIngredient(inventory.getIngredient("i05"), sizeOfDrink * COND_MILK_PORTION);
		// 8 gr Sugar
		recipe.addIngredient(inventory.getIngredient("i06"), sizeOfDrink * SUGAR_PORTION);
	}
}
