package com.fruitdrnk.transaction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.fruitdrnk.inventory.DataLoaderStrategy;
import com.fruitdrnk.inventory.Inventory;
import com.fruitdrnk.inventory.MockDataLoader;
import com.fruitdrnk.recipe.Recipe;

/**
 * JUnit Tests for the Transacion Class.
 * 
 * @author Marco Vaca
 */
public class TransactionTest {

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
		recipe = Recipe.generateNewRecipe();
		recipe.addIngredient(inventory.getIngredient("i01"), 100);
	}

	@Test
	public void testProcessTransaction() {
		// Create the Transaction
		Transaction tx = Transaction.createTransaction();

		// Process the Transaction
		tx.processTransaction(recipe, inventory);
		long actualStock = inventory.getStock("i01");

		// Should have decreased inventory size by 100 (Original 5000)
		assertEquals(4900, actualStock);
	}

	@Test
	public void testTransactionDenied() {
		// Create the Transaction
		Transaction tx = Transaction.createTransaction();

		// Huge quantity, just to check
		recipe.addIngredient(inventory.getIngredient("i01"), 10000);

		// Process the Transaction
		// Should not have decreased inventory
		// also, method return should be false
		assertFalse(tx.processTransaction(recipe, inventory));
		// long actualStock = inventory.getStock("i01");
		// assertEquals(4900, actualStock);
	}

}
