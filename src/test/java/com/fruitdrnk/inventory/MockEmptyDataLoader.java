package com.fruitdrnk.inventory;

import java.util.Map;

/**
 * Data loader used for tests. Loads NO Ingredients
 * 
 * @author Marco Vaca
 */
public class MockEmptyDataLoader implements DataLoaderStrategy {

	public Map<String, InventoryItem> populateInventory(String notUsed) {
		return populateInventory();
	}

	public Map<String, InventoryItem> populateInventory() {

		// A null map
		return null;
	}
}
