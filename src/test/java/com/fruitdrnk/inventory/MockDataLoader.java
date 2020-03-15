package com.fruitdrnk.inventory;

import java.util.HashMap;
import java.util.Map;

import com.fruitdrnk.product.Ingredient;
import com.fruitdrnk.product.UnitsOfMeasure;

/**
 * Data loader used for tests. Loads four Ingredients
 * 
 * @author Marco Vaca
 */
public class MockDataLoader implements DataLoaderStrategy {

	public Map<String, InventoryItem> populateInventory(String notUsed) {
		return populateInventory();
	}

	public Map<String, InventoryItem> populateInventory() {

		Map<String, InventoryItem> m = new HashMap<String, InventoryItem>();

		// 1st ingredient
		Ingredient igt = new Ingredient();
		igt.setIngredientCode("i01");
		igt.setName("Strawberry");
		igt.setUnitOfMeasure(UnitsOfMeasure.GRAMS);

		InventoryItem item = new InventoryItem(igt, 5000);

		m.put(igt.getIngredientCode(), item);

		// 2nd ingredient
		igt = new Ingredient();
		igt.setIngredientCode("i02");
		igt.setName("Banana");
		igt.setUnitOfMeasure(UnitsOfMeasure.GRAMS);

		item = new InventoryItem(igt, 6000);

		m.put(igt.getIngredientCode(), item);

		// 3rd ingredient
		igt = new Ingredient();
		igt.setIngredientCode("i03");
		igt.setName("Milk");
		igt.setUnitOfMeasure(UnitsOfMeasure.MILLILITERS);

		item = new InventoryItem(igt, 80000);

		m.put(igt.getIngredientCode(), item);

		// 4th ingredient
		igt = new Ingredient();
		igt.setIngredientCode("i04");
		igt.setName("Ice");
		igt.setUnitOfMeasure(UnitsOfMeasure.MILLILITERS);

		item = new InventoryItem(igt, 80000);

		m.put(igt.getIngredientCode(), item);

		//System.out.println("Size of inventory: " + m.size());

		return m;
	}

	/*
	 * public static void main(String[] args) { MockDataLoader idf = new
	 * MockDataLoader(); idf.populateInventory(); }
	 */
}
