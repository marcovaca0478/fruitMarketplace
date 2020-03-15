package com.fruitdrnk.transaction;

import java.util.Map;
import java.util.Set;

import com.fruitdrnk.inventory.Inventory;
import com.fruitdrnk.product.Ingredient;
import com.fruitdrnk.recipe.Recipe;

/**
 * A Transaction (sale) for the Fruit Drink Marketplace.
 * 
 * @author Marco Vaca
 */
public class Transaction {
	// Private to prevent direct instantiation
	private Transaction() {
	}

	/**
	 * Static instance generator
	 * 
	 * @return
	 */
	public static Transaction createTransaction() {
		Transaction tx = new Transaction();

		return tx;
	}

	/**
	 * Process the transaction for this Sale
	 * 
	 * @param recipe
	 * @param inventory
	 * @return
	 */
	public boolean processTransaction(Recipe recipe, Inventory inventory) {

		Map<Ingredient, Integer> ingredientsAndQuantities = recipe.getIngredientsAndQuantities();
		Set<Ingredient> ingredients = ingredientsAndQuantities.keySet();

		int quantityToUse = 0;
		int quantityInStock = 0;

		// First, we check availability for ALL ingredients
		// If one is not present, stock should NOT be reduced for any item
		for (Ingredient ingredient : ingredients) {
			quantityToUse = recipe.getIngredientQuantity(ingredient);
			quantityInStock = inventory.getStock(ingredient.getIngredientCode());

			// Check if we have enough ingredients
			checkStockThreshold(ingredient.getName(), quantityToUse, quantityInStock);

			// Impossible to prepare if not enough of even ONE ingredient
			if (quantityToUse > quantityInStock) {
				System.out.println("***** Not enough stock of " + ingredient.getName() + " (" + quantityInStock
						+ "). No sale was possible *****\n");
				return false;
			}
		}

		// Then, we iterate again to reduce stock
		for (Ingredient ingredient : ingredients) {
			quantityToUse = recipe.getIngredientQuantity(ingredient);

			// Reduce inventory
			inventory.decreaseItemStock(ingredient.getIngredientCode(), quantityToUse);
		}

		return true;
	}

	// Check for a threshold
	private void checkStockThreshold(String ingredientName, int quantityToUseInRecipe, int quantityInStock) {
		if ((Inventory.MINIMUM_DRINK_THRESHOLD * quantityToUseInRecipe) >= quantityInStock)
			System.out.println("Warning! Stock of " + ingredientName + " is LOW.");
	}

}
