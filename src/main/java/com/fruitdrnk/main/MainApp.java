package com.fruitdrnk.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import com.fruitdrnk.inventory.DataLoaderStrategy;
import com.fruitdrnk.inventory.FileDataLoader;
import com.fruitdrnk.inventory.Inventory;
import com.fruitdrnk.inventory.InventoryItem;
import com.fruitdrnk.recipe.Recipe;
import com.fruitdrnk.recipe.RecipeBook;
import com.fruitdrnk.transaction.Transaction;
import com.fruitdrnk.util.Utils;

/**
 * Main Class for the Exercise. Simple implementation of a command line
 * application.
 * 
 * @author Marco Vaca
 */
public class MainApp {

	private static DataLoaderStrategy loader;
	private static Inventory inventory;
	private static RecipeBook recipeBook;

	private static Scanner scanner;

	// Starter
	public static void main(String[] args) {
		// Load the Data
		loader = new FileDataLoader();

		// Handle file
		if (1 == args.length) {
			String fileName = args[0];
			inventory = Inventory.generateInventory(loader, fileName);
		} else
			inventory = Inventory.generateInventory(loader);

		recipeBook = new RecipeBook(inventory);

		mainMenu();
		scanner.close();
	}

	// Main menu
	private static void mainMenu() {
		while (true) {
			System.out.println("Main Menu:");
			System.out.println("1) Display Inventory");
			System.out.println("2) Sell Drink");
			System.out.println("3) Exit");

			scanner = new Scanner(System.in);

			String option = scanner.nextLine();

			switch (option) {
			case "1":
				menuDisplayInventory();
				break;
			case "2":
				menuSellDrink();
				break;
			case "3":
				return;
			default:
				System.out.println("Invalid option");
				continue;
			}
		}
	}

	// Menu to List the Inventory
	private static void menuDisplayInventory() {
		System.out.println("------------ Inventory ------------");
		System.out.println("  (cod) Item Name           , Qty., Unit of Measure");

		Map<String, InventoryItem> items = inventory.getItems();

		Set<String> setOfCodes = items.keySet();

		for (String currCode : setOfCodes) {
			System.out.println("* (" + currCode + ") " + Utils.rightPadString(items.get(currCode).getName(), 20) + ", "
					+ items.get(currCode).getQuantity() + ", " + items.get(currCode).getUnitOfMeasure());
		}

		System.out.println("---------------------------");
		System.out.println("");
	}

	// Sell a Drink
	private static void menuSellDrink() {
		scanner = new Scanner(System.in);

		// Initiate selling process
		menuWhile: while (true) {
			System.out.println("Choose drink flavor:");
			System.out.println("1) Strawberry");
			System.out.println("2) Banana");
			System.out.println("3) Mango");
			System.out.println("4) Mixed Flavor");
			System.out.println("5) Return to main menu");
			String option = scanner.nextLine();
			switch (option) {
			case "1":
				sellStrawberryDrink();
				break;
			case "2":
				sellBananaDrink();
				break;
			case "3":
				sellMangoDrink();
				break;
			case "4":
				menuSellMixedDrink();
				break;
			case "5":
				break menuWhile;
			default:
				System.out.println("Invalid option");
				continue;
			}
		}

	}

	// ------------------------------------------------------------------------------------
	// ---- THE FOLLOWING METHOD IS INCOMPLETE AND TO BE REFACTORED. I ran out of time ----
	// ------------------------------------------------------------------------------------

	// Sell a Mango Drink in different sizes
	// TODO: This could be refactored into a single method of sales
	// I ran out of time
	/*
	private static void sellMangoDrinkBySize() {
		Transaction tx = Transaction.createTransaction();
		Recipe mangoRecipe;

		// Initiate selling process
		menuWhile: while (true) {
			System.out.println("Choose drink Size:");
			System.out.println("1) Regular (300 ml)");
			System.out.println("2) Medium  (500 ml)");
			System.out.println("3) Large   (800 ml)");
			System.out.println("4) Return to choose a flavor");

			String option = scanner.nextLine();
			switch (option) {
			case "1":
				mangoRecipe = recipeBook.getMangoDrinkRecipe(Recipe.REGULAR_SIZE);
				if (tx.processTransaction(mangoRecipe, inventory))
					System.out.println("One *Regular Mango Drink* sold!\n");
				return;
			case "2":
				mangoRecipe = recipeBook.getMangoDrinkRecipe(Recipe.MEDIUM_SIZE);
				if (tx.processTransaction(mangoRecipe, inventory))
					System.out.println("One *Medium Mango Drink* sold!\n");
				return;
			case "3":
				mangoRecipe = recipeBook.getMangoDrinkRecipe(Recipe.LARGE_SIZE);
				if (tx.processTransaction(mangoRecipe, inventory))
					System.out.println("One *Large Mango Drink* sold!\n");
				return;
			case "4":
				break menuWhile;
			default:
				System.out.println("Invalid option");
				continue;
			}
		}
	}
	*/

	// -------------------------------------------------------------------------------------

	// Sell a Strawberry Drink
	private static void sellStrawberryDrink() {
		Transaction tx = Transaction.createTransaction();
		Recipe regularStrawberryRecipe = recipeBook.getStrawberryDrinkRecipe(Recipe.REGULAR_SIZE);

		if (tx.processTransaction(regularStrawberryRecipe, inventory))
			System.out.println("One *Regular Strawberry Drink* sold!\n");
	}

	// Sell a Banana Drink
	private static void sellBananaDrink() {
		Transaction tx = Transaction.createTransaction();
		Recipe regularBananaRecipe = recipeBook.getBananaDrinkRecipe(Recipe.REGULAR_SIZE);

		if (tx.processTransaction(regularBananaRecipe, inventory))
			System.out.println("One *Regular Banana Drink* sold!\n");
	}

	// Sell a Mango Drink
	private static void sellMangoDrink() {
		Transaction tx = Transaction.createTransaction();
		Recipe regularMangoRecipe = recipeBook.getMangoDrinkRecipe(Recipe.REGULAR_SIZE);

		if (tx.processTransaction(regularMangoRecipe, inventory))
			System.out.println("One *Regular Mango Drink* sold!\n");
	}

	// Sell a Mixed Drink
	private static void menuSellMixedDrink() {
		List<String> fruitCodes = new ArrayList<String>();

		while (true) {
			System.out.println("Mixed Drinks available:");
			System.out.println("1) Strawberry + Mango");
			System.out.println("2) Strawberry + Banana");
			System.out.println("3) Banana + Mango");
			System.out.println("4) Strawberry + Banana + Mango");
			System.out.println("5) Return to simple Drinks");

			scanner = new Scanner(System.in);

			String option = scanner.nextLine();

			switch (option) {
			case "1":
				fruitCodes.add("i01");
				fruitCodes.add("i02");
				sellMixedDrink(fruitCodes);
				break;
			case "2":
				fruitCodes.add("i01");
				fruitCodes.add("i03");
				sellMixedDrink(fruitCodes);
				break;
			case "3":
				fruitCodes.add("i02");
				fruitCodes.add("i03");
				sellMixedDrink(fruitCodes);
				break;
			case "4":
				fruitCodes.add("i01");
				fruitCodes.add("i02");
				fruitCodes.add("i03");
				sellMixedDrink(fruitCodes);
				break;
			case "5":
				return;
			default:
				System.out.println("Invalid option");
				continue;
			}
		}
	}

	// Sell a Mixed Drink
	private static void sellMixedDrink(List<String> fruitCodes) {
		Transaction tx = Transaction.createTransaction();
		Recipe regularMixedRecipe = recipeBook.getMixedDrinkRecipe(fruitCodes, Recipe.REGULAR_SIZE);

		if (tx.processTransaction(regularMixedRecipe, inventory))
			System.out.println("One *Mixed Drink* sold!\n");
	}
}
