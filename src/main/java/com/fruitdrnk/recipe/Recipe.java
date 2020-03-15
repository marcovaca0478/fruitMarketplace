package com.fruitdrnk.recipe;

import java.util.HashMap;
import java.util.Map;

import com.fruitdrnk.product.Ingredient;

/**
 * A Recipe
 * 
 * @author Marco Vaca
 */
public class Recipe {

	public static final int REGULAR_SIZE = 3;
	public static final int MEDIUM_SIZE = 5;
	public static final int LARGE_SIZE = 8;

	// Map for Ingredients
	private Map<Ingredient, Integer> ingredientsAndQuantities;

	// Private constructor to prevent direct instantiation
	private Recipe() {
	}

	public Map<Ingredient, Integer> getIngredientsAndQuantities() {
		return this.ingredientsAndQuantities;
	}

	public void setIngredientsAndQuantities(Map<Ingredient, Integer> ingredientsAndQuantities) {
		this.ingredientsAndQuantities = ingredientsAndQuantities;
	}

	/**
	 * Static creation of a new Recipe
	 * 
	 * @return
	 */
	public static Recipe generateNewRecipe() {
		Recipe recipe = new Recipe();

		Map<Ingredient, Integer> ingredientsAndQuantities = new HashMap<Ingredient, Integer>();
		recipe.setIngredientsAndQuantities(ingredientsAndQuantities);

		return recipe;
	}

	/**
	 * Get the quantity for the desired ingredient in this Recipe
	 * 
	 * @param ingredient
	 * @return
	 */
	public int getIngredientQuantity(Ingredient ingredient) {
		return this.ingredientsAndQuantities.get(ingredient).intValue();
	}

	/**
	 * Add an ingredient to Recipe
	 * Removes the ingredient if set to ZERO quantity
	 * 
	 * @param ingredient
	 * @param quantity
	 */
	public void addIngredient(Ingredient ingredient, int quantity) {
		// Check if there is no previous instance for this ingredient
		if (this.ingredientsAndQuantities.containsKey(ingredient)) {

			// Check for zero quantity, should delete if it already exists
			if (0 == quantity) {
				this.ingredientsAndQuantities.remove(ingredient);
			}

			// Update the quantity for the Ingredient
			this.ingredientsAndQuantities.replace(ingredient, new Integer(quantity));

		} else {
			// Check again for zero quantity
			if (0 == quantity) {
				return;
			} else {
				this.ingredientsAndQuantities.put(ingredient, new Integer(quantity));
			}
		}
	}
}
