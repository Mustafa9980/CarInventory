package carInventory;

import java.io.PrintWriter;
import java.util.InputMismatchException;

public class Car {
	private String carMake  = "";   // Stores Car Make
	private String carModel = "";   // Stores Car Model
	private String carColor = "";
	private int carPrice = 0;
	private String carPetName = "";

	public void setCarMake() {
		String newMake = "";
		System.out.println("Input Basic Car Information");
                System.out.print("Enter car make: ");
		newMake = Inventory.scan.nextLine();
		carMake = newMake;
		Inventory.errorFixed = true;//If an error occured and we need to fix it

		return;
	}

	public void setCarModel() {   // carModel setter method
		String newModel = "";   // Stores new Car Model

		System.out.print("Enter car model: ");  
		newModel = Inventory.scan.nextLine();
		carModel = newModel;   // Assigns new Car Model to new Car Object
		return;
	}

	public void setCarColor() {   // carColor setter method
		String newColor = "";   // Stores new Car Color

		System.out.print("Enter car color: ");  
		newColor = Inventory.scan.nextLine();
		carColor = newColor;   // Assigns new Car Color to new Car Object
		return;
	}

	public void setCarPrice(){   //  setter method
		boolean rightInput = false;   // Verifies if user input is correct
		int newPrice = 0;   // Stores new Car Price

		do {   // loops until user input is correct
			try {
				System.out.print("Enter car Base Price: ");
				newPrice = Inventory.scan.nextInt();
				Inventory.scan.nextLine();   // fixes skip after integer scan.
				carPrice = newPrice;   // Assigns new Car Price to new Car Object
				rightInput = true;   // User input is correct
			} catch (InputMismatchException e) {   // Catches incorrect input from user
				System.out.println ("You entered an invalid input.");
				Inventory.scan.nextLine();
			}
		} while(!rightInput);//If the  input was not right

		return;
	}

	public void setCarPetName(){   // car pet name setter method
		boolean rightInput = false;   // Verifies if user input is correct
		String newPetName = "";   // Stores new Car pet name

		do {   // loops until user input is correct
			try {
				System.out.print("Enter car PetName: ");
				newPetName = Inventory.scan.next();
				Inventory.scan.nextLine();   // fixes skip after integer scan.
				carPetName = newPetName;   // Assigns new Car Pet Name to new Car Object
				rightInput = true;   // User input is correct
			} catch (InputMismatchException e) {   // catches incorrect input from user
				System.out.println ("You entered an invalid input.");
				Inventory.scan.nextLine();
			}
		} while(!rightInput);   // End of the loop

		return;
	}

	public void print(){   // print on console method
		System.out.printf("%-13s\t%-13s\t%-20s\t%-7s\t%9s\n", carMake, carModel, carColor, carPrice, carPetName);
         //%13s prints out the string, left-padded by spaces until the total length is 13.       
	}

	public void printText(PrintWriter printInv){   // Print inventory on text file
		printInv.printf("%-13s\t%-13s\t%-20s\t%-7s\t%9s\n", carMake, carModel, carColor, carPrice, carPetName);
        //%13s prints out the string, left-padded by spaces until the total length is 13.        
	}

	public Car(){   //Car class constructor
		carMake    = "";
		carModel   = "";
		carColor   = "";
		carPrice    = 0;
		carPetName = "";
	}
}