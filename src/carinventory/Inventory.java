package carInventory;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;

import java.util.InputMismatchException;
import java.util.ArrayList;
import java.util.Scanner;

public class Inventory {
	public final static Scanner scan = new Scanner(System.in);   // Public Scanner object to fix NoSuchElementException.
	public static boolean errorFixed = false;   //  try-catch loop condition. Verifies if exception if fixed
	public static void addCar(ArrayList<Car> cars, Car newEntry) {
		boolean rightInput = false;
		int carsToAdd = 0;
		int i = 0;

		do {
			try {
				carsToAdd = 1;
				rightInput = true;
			} catch (InputMismatchException e) {
				System.out.println("You entered an invalid input");
				scan.nextLine();
			}
		} while (!rightInput);

		for (i = 0; i < carsToAdd; i++) {   // Iterates through creating each car and assigning attributes
			newEntry = new Car();  // Creates newEntry of type Car
			cars.add(newEntry);  // Add newEntry to ArrayList

			if (i >= 1) {   //Changes the prompt message if user wants to add more than 1 car
				System.out.println("Enter info for next car...");
			}
			newEntry.setCarMake();  // Assign newEntry's attributes
			newEntry.setCarModel();
			newEntry.setCarPrice();
			newEntry.setCarColor();
			newEntry.setCarPetName();

			System.out.println("");
		}
	}

	public static void deleteCar(ArrayList<Car> cars) {   //Method to delete car from the inventory
		int invNumber;   // Inventory number displayed on the table
		int entryIndex;   // ArrayList index

		System.out.println("Which inventory number would you like to delete?");
		System.out.print("--> ");
		invNumber = scan.nextInt();
		scan.nextLine();
		entryIndex = invNumber - 1;  // Computes the index from the inventory number

		cars.remove(entryIndex);  // remove car entry
	}

	/*
	 * Modifies car attributes from the inventory
	 */

	public static void modifyInv(ArrayList<Car> cars) {   //Method to modify car attributes
		boolean checkInput;  // Verifies if the user has made a valid attribute selection
		int carAttribute;     // Car attribute options
		int invNumber;     // Inventory number displayed on the table
		int entryIndex;     // ArrayList index

		System.out.println("Which inventory number would you like to modify?");   // Prompts user to enter the inventory number
		System.out.print("--> ");
		invNumber = scan.nextInt();
		scan.nextLine();   // To prevent skip after integer scan
		entryIndex = invNumber - 1;   // Computes the index from the inventory number

		/*
		 * Iterates through the command prompt until user enters
		 * a valid attribute to modify
		 */
		do {   // Start do-while loop
			System.out.println("Which attribute would you like to modify?");
			System.out.println("1: Make   2: Model   3: Color   4: Price   5: Pet Name");
			System.out.print("--> ");
			carAttribute = scan.nextInt();
			scan.nextLine();   // To prevent skip after integer scan

			switch (carAttribute) {
				case 1:
					cars.get(entryIndex).setCarMake();  // Modify car Make
					checkInput = false;
					break;
				case 2:
					cars.get(entryIndex).setCarModel();   // Modify car Model
					checkInput = false;
					break;
				case 3:
					cars.get(entryIndex).setCarColor();   // Modify car Color
					checkInput = false;
					break;
				case 4:
					cars.get(entryIndex).setCarPrice();   // Modify car Price
					checkInput = false;
					break;
				case 5:
					cars.get(entryIndex).setCarPetName();   // Modify car Pet Name
					checkInput = false;
					break;
				default:
					System.out.println("Please verify your input.");
					checkInput = true;
			}
		} while (checkInput);   // End do-while loop

	}

	/**
	 * Prints the inventory table on the console.
	 *
	 */

	public static void printCons(ArrayList<Car> cars) {
		int entryIndex;  // index counter

		//Print table header
		System.out.printf("%5s\t%-13s\t%-13s\t%-20s\t%-7s\t%9s\n", "Car ID", "Make", "Model", "Color", "Price", "Pet Name");
		System.out.println("------\t-------------\t-------------\t--------------------\t-------\t---------\t");
		//Print table entries
		for (entryIndex = 0; entryIndex < cars.size(); entryIndex++) {   // Iterates through each entry and prints on console
			System.out.printf("%5s\t", entryIndex + 1);
			cars.get(entryIndex).print();
		}
		System.out.println("------\t-------------\t-------------\t--------------------\t-------\t---------\t");
	}

	/**
	 * Prints the inventory table on a text file.
	 */

	public static void printText(ArrayList<Car> cars) {
		File invFile = new File("CurrentInventory.txt");  // Creates text file CurrentInventory if file does not exist.
		int entryIndex;  // Index counter

		try {
			PrintWriter printInv = new PrintWriter(invFile);

			//Print table header
			printInv.println("\t\t* * * The Autolot Console UI * * *");  // Program title
			printInv.println("");
			printInv.println("Dealership Name: .................    Inventory Date: .. / .. / ..  ");
			printInv.println("");
			printInv.printf("%5s\t%-13s\t%-13s\t%-20s\t%-7s\t%-9s\n", "Inv", "Make", "Model", "Color", "Price", "Pet Name");
			printInv.println("------\t-------------\t-------------\t--------------------\t-------\t---------\t");
			//Print table entries
			for (entryIndex = 0; entryIndex < cars.size(); entryIndex++) {
				printInv.printf("%5s\t", entryIndex + 1);
				cars.get(entryIndex).printText(printInv);   // Iterates through the entries and prints on the text file
			}
			printInv.println("------\t-------------\t-------------\t--------------------\t-------\t---------\t");

			printInv.close();
		} catch (IOException ex) {  //
			System.out.printf("Error: %s\n", ex);
		}
	}
	public static void main(String[] args) {
		Car newCar = new Car();   // Creates variable newCar of type Car for new entries
		boolean maintainInv = true;   // while condition, resets loop until user Quit Program
		boolean commandCheck = true;   // do-while condition, resets loop after each command completion. Exit loop at Quit Program
		String commandEntry="";   // switch case option for user command.

		ArrayList<Car> carList = new ArrayList<>();

		System.out.println("\t\t* * * The Autolot Console UI * * *");
                System.out.println("\t\t* * * This Program Is Developed By: Mustafa * * *");
		System.out.println(" ");
		printCons(carList);
		System.out.println(" ");

		while (maintainInv) {

			System.out.println(" ");
			System.out.println("I: Inserts a new sedan.\nU: Updates car pet name.\nD: Delete an existing sedan.\nL: Lists current Inventory.\nQ: Quits Program.");  // Command options
			System.out.print("\n\nPlease enter your choice: ");
			do {
				try {
					commandEntry = scan.next();
					scan.nextLine();
					errorFixed = true;
				} catch (InputMismatchException e) {
					System.out.println("You entered an invalid input.");
					System.out.println("Please select between the given choices...");
					scan.next();
				}
			} while (!errorFixed);
			
                        do {
				switch (commandEntry) {
					case "I":
						addCar(carList, newCar);
						printCons(carList);
						commandCheck = false;
						break;
					case "U":
						if (carList.size() >= 1) {
							modifyInv(carList);
							printCons(carList);
							commandCheck = false;
						} else {
							System.out.println("The inventory is currently empty.");
							System.out.println(" ");
							commandCheck = false;
						}
						break;
					
                                        case "D":
						if (carList.size() >= 1) {
							deleteCar(carList);
							printCons(carList);
							commandCheck = false;
						} else {
							System.out.println("The inventory is currently empty.");
							System.out.println(" ");
							commandCheck = false;
						}
						break;
					case "L":
						printText(carList);
						System.out.println("CurrentInventory.text has been created in the project folder....");
						System.out.println(" ");
						printCons(carList);
						commandCheck = false;
						break;
					case "Q":
						commandCheck = false;
						maintainInv = false;
						break;
					default:

						do {
							try {
								System.out.println("Please select between the given command options.");
								System.out.print("--> ");
								commandEntry = scan.next();
								scan.nextLine();
								commandCheck = true;
								errorFixed = true;
							} catch (InputMismatchException e) {
								System.out.println("You entered an invalid input.");
								scan.next();   // fixes try-catch infinite loop
							}
						} while (!errorFixed);   // END do-while loop to verify user command input

				}

			} while (commandCheck);
			// END DO-WHILE LOOP TO VERIFY USER ACTIVITY IF USE/QUIT

		}  // END While loop for Command prompt loop

		System.out.println("Exiting program . . .");
		System.out.println("Good bye..\n\t\t\t\t\t**Mutafa**!");

		scan.close();

	}
}
