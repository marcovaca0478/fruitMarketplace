package com.fruitdrnk.inventory;

import java.util.Collection;
import java.util.Map;

import com.fruitdrnk.product.Ingredient;

/**
 * Inventory for Marco's Fruit Drink marketplace For this particular
 * implementation, we only have to load the inventory hard coding the quantities
 * in the code.
 * 
 * Thus, I chose to implement is simply done via a Map
 * 
 * @author Marco Vaca
 */
public class Inventory {

	public static final int MINIMUM_DRINK_THRESHOLD = 4;

	// The actual "stock" for the Fruit Marketplace
	private Map<String, InventoryItem> items;

	// Default constructor is marked as private to prevent use
	// Instead, we will populate it via the data loader Strategy
	private Inventory() {
	}

	// Inventory generation via Strategy (data load)
	public static Inventory generateInventory(DataLoaderStrategy loader) {
		Inventory inventory = new Inventory();
		inventory.items = loader.populateInventory();

		return inventory;
	}

	// Inventory generation via Strategy (data load). Also takes a pathAndFilename
	public static Inventory generateInventory(DataLoaderStrategy loader, String pathAndFilename) {
		Inventory inventory = new Inventory();
		inventory.items = loader.populateInventory(pathAndFilename);

		return inventory;
	}

	/**
	 * Used to count the number of DIFFERENT items available on stock
	 * 
	 * Not to be confused with the TOTAL quantity for each item
	 */
	public int numberOfInventoryItems() {
		return this.items == null ? 0 : this.items.size();
	}

	/**
	 * Used to count the total quantity of ALL inventory items available on stock
	 * 
	 * @return
	 */
	public int totalQuantityOfInventoryItems() {
		if (null == this.items)
			return 0;

		int totalQuantity = 0;

		Collection<InventoryItem> values = items.values();

		for (InventoryItem inventoryItem : values) {
			totalQuantity += inventoryItem.getQuantity();
		}
		return totalQuantity;
	}

	/**
	 * Gets the stock for a given item code
	 * 
	 * @param itemCode
	 * @return
	 */
	public int getStock(String itemCode) {
		InventoryItem item = this.items.get(itemCode);
		return item.getQuantity();
	}

	/**
	 * Increases the stock for a given item
	 * 
	 * @param itemCode
	 * @param quantityToIncrease
	 */
	public void increaseItemStock(String itemCode, int quantityToIncrease) {
		InventoryItem item = this.items.get(itemCode);
		item.increaseQuantity(quantityToIncrease);

		items.replace(itemCode, item);
	}

	/**
	 * Decreases the stock for a given item
	 * 
	 * @param itemCode
	 * @param quantityToDecrease
	 * @return
	 */
	public boolean decreaseItemStock(String itemCode, int quantityToDecrease) {
		InventoryItem item = this.items.get(itemCode);

		// Impossible to decrease Stock if not enough items
		if (quantityToDecrease > item.getQuantity()) {
			// System.out.println("Not enough stock! No changes were made.");
			return false;
		}

		item.decreaseQuantity(quantityToDecrease);
		items.replace(itemCode, item);

		return true;
	}

	// Gets an Ingredient, given the ingredient code
	public Ingredient getIngredient(String ingredientCode) {
		InventoryItem item = this.items.get(ingredientCode);

		if (null == item)
			return null;
		else
			return item.getIngredient();
	}

	// Returns the Map of Items
	public Map<String, InventoryItem> getItems() {
		return this.items;
	}

}
