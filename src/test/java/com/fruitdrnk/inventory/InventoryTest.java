package com.fruitdrnk.inventory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.fruitdrnk.product.Ingredient;

/**
 * JUnit Tests for the Inventory Class.
 * 
 * @author Marco Vaca
 */
public class InventoryTest {
	private static DataLoaderStrategy loader;
	private static Inventory inventory;

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

	/*
	 * @Before public void setUp() throws Exception { }
	 * 
	 * @After public void tearDown() throws Exception { }
	 */

	@Test
	public void testNumberOfItemsInventory() {
		// Mock loader has 4 items
		Long l = new Long(inventory.numberOfInventoryItems());

		assertEquals(4, l.longValue());
	}

	@Test
	public void testGetANonexistingIngredient() {
		// Should return null for this code
		Ingredient igt = inventory.getIngredient("XXX");

		assertNull(igt);
	}

	@Test
	public void testGetAnExistingIngredient() {
		// Code for Milk is "i03", from Mock Loader
		Ingredient igt = inventory.getIngredient("i03");

		assertEquals("i03", igt.getIngredientCode());
	}

	@Test
	public void testGetStockOfMilk() {
		// Milk has 80.000 milliliters in stock
		// Code is "i03", from Mock Loader
		Long stock = new Long(inventory.getStock("i03"));

		assertEquals(80000, stock.longValue());
	}

	@Test
	public void testIncreaseStockOfIngredient() {
		int fourThousandBananaGrams = 4000;

		// We increment 4000 grams of Bananas
		// Code is "i02", from Mock Loader
		inventory.increaseItemStock("i02", fourThousandBananaGrams);
		Long currentStock = new Long(inventory.getStock("i02"));

		assertEquals(10000, currentStock.longValue());
	}

	@Test
	public void testDecreaseStockOfIngredient() {
		int fiveHundredStrawberryGrams = 500;

		// 500 mg of Strawberries have been used
		// Code is "i01", from Mock Loader
		assertTrue(inventory.decreaseItemStock("i01", fiveHundredStrawberryGrams));

		Long currentStock = new Long(inventory.getStock("i01"));

		assertEquals(4500, currentStock.longValue());
	}

	@Test
	public void testDecreaseStockOfProductBeyondExistences() {
		int tenThousandStrawberryGrams = 10000;

		assertFalse(inventory.decreaseItemStock("i01", tenThousandStrawberryGrams));

		Long currentStock = new Long(inventory.getStock("i01"));

		assertEquals(4500, currentStock.longValue());
	}

	@Test
	public void testGrossSizeOfInventory() {
		// Mock loader has 170.500 units of items in stock
		// Note that for the purposes of this test, I don't differentiate units of
		// measure
		Long l = new Long(inventory.totalQuantityOfInventoryItems());

		assertEquals(170500, l.longValue());
	}

}
