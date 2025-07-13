package org.example.visitor;

import org.example.Helper;
import org.example.animal.Animal;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class GuestMain {
    private final Scanner INPUT = new Scanner(System.in);
    private final Helper HELPER = Helper.getInstance();

    //  all options for user
    public void showMainMenu() {
        while (true) {
            System.out.println("\n=== Attractions ===");
            System.out.println("What would you like to do?");
            System.out.println("1. Visit Enclosure");
            System.out.println("2. Visit Shop");
            System.out.println("3. Visit Hospital");
            System.out.println("4. Leave Zoo");
            System.out.print("Choose an option: ");

            int choice = Integer.parseInt(INPUT.nextLine());
            switch (choice) {
                case 1 -> visitEnclosure();
                case 2 -> visitShop();
                case 4 -> {
                    break;
                }
                //  input validation
                default -> System.out.println("Invalid option. Please try again.");
            }

            //  if user wanted to exit the attraction model
            if (choice == 4) {
                System.out.println("\nYou have left the zoo.");
                break;
            }
        }
    }

    //  ==================  ENCLOSURE  ===================================
    private void visitEnclosure() {
        System.out.println("\n=== Zoo Enclosure ===");
        System.out.println("Choose Enclosure:");
        System.out.println("1. Pachyderm");
        System.out.println("2. Feline");
        System.out.println("3. Bird");
        System.out.print("Choose an option: ");

        int enclosureChoice = Integer.parseInt(INPUT.nextLine());
        //  dynamic displayed content based on the selected species
        displayAnimalsByEnclosure(enclosureChoice);
    }

    //  function for displaying the list of animals based on health and species
    private void displayAnimalsByEnclosure(int enclosureOption) {
        //  primary storage for healthy animals
        List<Animal> healthyAnimals;

        //  each case calls the HELPER singleton function for fetching healthy animals
        switch (enclosureOption) {
            //  retrieves healthy elephants
            case 1 -> {
                System.out.println("\n=== Pachyderm ===");
                healthyAnimals = HELPER.getHealthyElephants();
            }
            //  retrieves healthy lions
            case 2 -> {
                System.out.println("\n=== Feline ===");
                healthyAnimals = HELPER.getHealthyLion();
            }
            //  retrieves healthy owls
            case 3 -> {
                System.out.println("\n=== Bird ===");
                healthyAnimals = HELPER.getHealthyOwl();
            }
            //  input validation
            default -> {
                System.out.println("Invalid enclosure option.");
                return;
            }
        }

        //  displays if no healthy animals were found for the selected species
        if (healthyAnimals.isEmpty()) {
            System.out.println("Sorry, all animals here are with the veterinarian.");
            return;
        }

        // calls the function for displaying all the healthy animals based on the selected species
        promptAnimalSelection(healthyAnimals);
    }

    private void promptAnimalSelection(List<Animal> animals) {
        //  list all the healthy animals
        for (int i = 0; i < animals.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, animals.get(i).name);
        }

        //  prompting the user to select an animal
        System.out.print("Choose an animal by number: ");
        int selectedIndex = Integer.parseInt(INPUT.nextLine()) - 1;

        //  input validation
        if (selectedIndex < 0 || selectedIndex >= animals.size()) {
            System.out.println("Invalid selection.");
            return;
        }

        //  fetching the selected animal then storing it in a single Animal instance
        Animal selectedAnimal = animals.get(selectedIndex);
        System.out.printf("Would you like to feed the %s (yes/no): ", selectedAnimal.name);
        String answer = INPUT.nextLine();

        //  performing animal action based on the user's choice
        if (answer.equalsIgnoreCase("yes")) {
            performAnimalActions(selectedAnimal);
        } else {
            performSadAnimalAction(selectedAnimal);
        }
    }

    //  function for displaying sad animal actions
    private static void performSadAnimalAction(Animal animal) {
        System.out.println();
        System.out.printf("%s is sad.%n", animal.name);
        animal.makeSound();
        animal.sleep();
        System.out.println();
    }

    //  function for displaying happy animal actions
    private static void performAnimalActions(Animal animal) {
        System.out.println();
        animal.eat();
        animal.makeSound();
        System.out.println();
    }
    //  ========================================================================



    //  ==================  SHOP  ===================================
    private void visitShop() {
        //  storage for all the selected items
        HashMap<String, Integer> selectedFoods = new HashMap<>();

        //  constant values for item's prices
        final int SOFT_DRINK = 30;
        final int POPCORN = 50;
        final int PLUSH_TOY = 120;
        final int KEYCHAIN = 45;

        //  total amount for the chosen items
        int totalAmount = 0;

        while (true) {
            //  prompt user display
            System.out.println("\n=== Zoo Shop ===");
            System.out.println("Available Products");
            System.out.println("1. Soft Drink - ₱30");
            System.out.println("2. Popcorn - ₱50");
            System.out.println("3. Plush Toy - ₱120");
            System.out.println("4. Keychain - ₱45");
            System.out.println("5. Proceed to Checkout");

            System.out.print("Enter the number of the item you want to buy: ");
            int chosenFoodIndex = Integer.parseInt(INPUT.nextLine());

            //  placeholder for selected item's name and price
            String itemName = null;
            int itemPrice = 0;

            //  chance the itemName and itemPrice based on the selected item
            switch (chosenFoodIndex) {
                case 1 -> {
                    itemName = "Soft Drink";
                    itemPrice = SOFT_DRINK;
                }
                case 2 -> {
                    itemName = "Popcorn";
                    itemPrice = POPCORN;
                }
                case 3 -> {
                    itemName = "Plush Toy";
                    itemPrice = PLUSH_TOY;
                }
                case 4 -> {
                    itemName = "Keychain";
                    itemPrice = KEYCHAIN;
                }
                case 5 -> {
                    break;
                }
                //  input validation
                default -> {
                    System.out.println("Invalid option. Please choose again.");
                    continue;
                }
            }

            //  exit shop function
            if (chosenFoodIndex == 5) break;

            //  change the storage's values based on the selected item
            //  getOrDefault gets the value of the key, then adds immediately after the itemPrice
            selectedFoods.put(itemName, selectedFoods.getOrDefault(itemName, 0) + itemPrice);
            //  increment the total amount based on the selected items' amount
            totalAmount += itemPrice;

            //  display all the uniquely selected item along with the total amount for each of the items
            System.out.println("\nSelected:");
            selectedFoods.forEach((s, n) -> System.out.printf("%s - ₱%d%n", s, n));
        }

        //  displays the checking summary for the purchase
        System.out.println("\n=== Checkout Summary ===");
        selectedFoods.forEach((s, n) -> System.out.printf("%s - ₱%d%n", s, n));
        System.out.println("Total Amount: ₱" + totalAmount);
    }
    //  ========================================================================
}