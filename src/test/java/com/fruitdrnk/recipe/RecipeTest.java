package com.fruitdrnk.recipe;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.fruitdrnk.inventory.DataLoaderStrategy;
import com.fruitdrnk.inventory.Inventory;
import com.fruitdrnk.inventory.MockDataLoader;

/**
 * JUnit Tests for the Recipe Class.
 * 
 * @author Marco Vaca
 */
public class RecipeTest {
	private static DataLoaderStrategy loader;
	private static Inventory inventory;
	private static Recipe recipe;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		loader = new MockDataLoader();
		inventory = Inventory.generateInventory(loader);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		loader = null;
		inventory = null;
	}

	@Before
	public void setUp() throws Exception {
		// Create new recipe
		recipe = Recipe.generateNewRecipe();
	}

	@After
	public void tearDown() throws Exception {
		// Erase recipe
		recipe = null;
	}

	@Test
	public void testCreateNewRecipe() {
		// Verify it is empty
		Long size = new Long(recipe.getIngredientsAndQuantities().size());
		assertEquals(0, size.longValue());
	}

	@Test
	public void testAddIngredient() {
		recipe.addIngredient(inventory.getIngredient("i01"), 100);

		// Verify Cart is now size 1
		Long size = new Long(recipe.getIngredientsAndQuantities().size());
		assertEquals(1, size.longValue());
	}

	@Test
	public void testAddSameIngredient() {
		recipe.addIngredient(inventory.getIngredient("i01"), 100);

		// When adding same ingredient, should update quantity
		recipe.addIngredient(inventory.getIngredient("i01"), 80);

		Long size = new Long(recipe.getIngredientQuantity(inventory.getIngredient("i01")));
		assertEquals(80, size.longValue());
	}

	@Test
	public void testAddIngredientWithZeroQuantity() {
		recipe.addIngredient(inventory.getIngredient("i01"), 0);

		// Verify Recipe is still empty
		Long size = new Long(recipe.getIngredientsAndQuantities().size());
		assertEquals(0, size.longValue());
	}

	@Test
	public void testUpdateIngredientWithZeroQuantity() {
		recipe.addIngredient(inventory.getIngredient("i01"), 100);

		// When updating ingredient with Zero quantity, should delete the ingredient
		recipe.addIngredient(inventory.getIngredient("i01"), 0);

		// Verify Recipe is still empty
		Long size = new Long(recipe.getIngredientsAndQuantities().size());
		assertEquals(0, size.longValue());
	}
}
