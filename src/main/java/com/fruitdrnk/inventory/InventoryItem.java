package com.fruitdrnk.inventory;

import com.fruitdrnk.product.Ingredient;

/**
 * An Inventory Item. The combination of an Ingredient and the quantity
 * available in stock.
 * 
 * @author Marco Vaca
 */
public class InventoryItem {
	private Ingredient ingredient;
	private int quantity;

	// Constructor
	public InventoryItem(Ingredient ingredient, int quantity) {
		this.ingredient = ingredient;
		this.quantity = quantity;
	}

	// Gets the inner Ingredient
	public Ingredient getIngredient() {
		return ingredient;
	}

	// Commented to prevent access
	/*
	 * public void setIngredient(Ingredient ingredient) { this.ingredient = ingredient; }
	 */

	// Actual quantity
	public int getQuantity() {
		return quantity;
	}

	/*
	 * // Used to set the new quantity public void setQuantity(int quantity) {
	 * this.quantity = quantity; }
	 */

	// Decrease stock
	public void decreaseQuantity(int quantityToDecrease) {
		this.quantity = this.quantity - quantityToDecrease;
	}

	// Increase stock
	public void increaseQuantity(int quantityToIncrease) {
		this.quantity = this.quantity + quantityToIncrease;
	}

	// --- INNER INGREDIENT METHODS

	// Gets the bar Code from the inner Ingredient
	public String getIngredientCode() {
		return this.ingredient.getIngredientCode();
	}

	// Gets the name from the inner Ingredient
	public String getName() {
		return this.ingredient.getName();
	}

	// Gets the unit of measure from the inner Ingredient
	public String getUnitOfMeasure() {
		return this.ingredient.getUnitOfMeasure();
	}
}
