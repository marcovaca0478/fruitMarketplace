package com.fruitdrnk.product;

/**
 * Ingredients. The *things* that are used as supplies within the Fruit Drink
 * Marketplace. For the purpose of this exercise, we use only the simplest of
 * properties for each Ingredient.
 * 
 * @author Marco Vaca
 */
public class Ingredient {

	// Attribute added to identify Ingredients
	private String ingredientCode;

	// List of attributes provided for the exercise
	private String name;
	private String unitOfMeasure;

	public String getIngredientCode() {
		return ingredientCode;
	}

	public void setIngredientCode(String ingredientCode) {
		this.ingredientCode = ingredientCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUnitOfMeasure() {
		return unitOfMeasure;
	}

	public void setUnitOfMeasure(String unitOfMeasure) {
		this.unitOfMeasure = unitOfMeasure;
	}
}
