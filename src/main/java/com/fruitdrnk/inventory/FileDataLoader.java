package com.fruitdrnk.inventory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fruitdrnk.product.Ingredient;

/**
 * Data loader via File.
 * 
 * @author Marco Vaca
 */
public class FileDataLoader implements DataLoaderStrategy {

	private static String PATH_TO_CSV_FILE = "D:\\users\\Marco\\eclipse-workspace\\fruitdrnk-mivs\\src\\main\\resources\\Inventory.csv";

	/**
	 * Method used within the program to determine the file location
	 */
	public Map<String, InventoryItem> populateInventory(String filePathAndName) {
		PATH_TO_CSV_FILE = filePathAndName;

		return populateInventory();
	}

	/**
	 * Populate the inventory
	 */
	public Map<String, InventoryItem> populateInventory() {
		Map<String, InventoryItem> items = new HashMap<String, InventoryItem>();

		String row;

		try {
			BufferedReader csvReader = new BufferedReader(new FileReader(PATH_TO_CSV_FILE));

			while ((row = csvReader.readLine()) != null) {
				String[] data = row.split(";");

				Ingredient igt = new Ingredient();
				igt.setIngredientCode(data[0]);
				igt.setName(data[1]);
				igt.setUnitOfMeasure(data[2]);

				int q = Integer.parseInt(data[3]);

				InventoryItem item = new InventoryItem(igt, q);

				items.put(item.getIngredientCode(), item);
			}

			// System.out.println("Size of inventory: " + items.size());

			csvReader.close();

			return items;

		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return null;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	/*
	 * public static void main(String[] args) { FileDataLoader fdl = new
	 * FileDataLoader(); fdl.populateInventory(); // "D:\\inv-test.csv" }
	 */

}
