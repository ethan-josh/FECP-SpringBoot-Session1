package org.example.guest;

import org.example.Helper;
import org.example.animal.Animal;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class GuestMain {
    private static final Scanner INPUT = new Scanner(System.in);
    private static final Helper HELPER = Helper.getInstance();

    public static void main(String[] args) {
        showMainMenu();
    }

    private static void showMainMenu() {
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
                default -> System.out.println("Invalid option. Please try again.");
            }

            if (choice == 4) {
                System.out.println("\nYou have left the zoo.");
                break;
            }
        }
    }

    //  ==================  ENCLOSURE  ===================================
    private static void visitEnclosure() {
        System.out.println("\n=== Zoo Enclosure ===");
        System.out.println("Choose Enclosure:");
        System.out.println("1. Pachyderm");
        System.out.println("2. Feline");
        System.out.println("3. Bird");
        System.out.print("Choose an option: ");

        int enclosureChoice = Integer.parseInt(INPUT.nextLine());
        displayAnimalsByEnclosure(enclosureChoice);
    }

    private static void displayAnimalsByEnclosure(int enclosureOption) {
        List<Animal> healthyAnimals;

        switch (enclosureOption) {
            case 1 -> {
                System.out.println("\n=== Pachyderm ===");
                healthyAnimals = HELPER.getHealthyElephants();
            }
            case 2 -> {
                System.out.println("\n=== Feline ===");
                healthyAnimals = HELPER.getHealthyLion();
            }
            case 3 -> {
                System.out.println("\n=== Bird ===");
                healthyAnimals = HELPER.getHealthyOwl();
            }
            default -> {
                System.out.println("Invalid enclosure option.");
                return;
            }
        }

        if (healthyAnimals.isEmpty()) {
            System.out.println("Sorry, all animals here are with the veterinarian.");
            return;
        }

        promptAnimalSelection(healthyAnimals);
    }

    private static void promptAnimalSelection(List<Animal> animals) {
        for (int i = 0; i < animals.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, animals.get(i).name);
        }

        System.out.print("Choose an animal by number: ");
        int selectedIndex = Integer.parseInt(INPUT.nextLine()) - 1;

        if (selectedIndex < 0 || selectedIndex >= animals.size()) {
            System.out.println("Invalid selection.");
            return;
        }

        Animal selectedAnimal = animals.get(selectedIndex);
        System.out.printf("Would you like to feed the %s (yes/no): ", selectedAnimal.name);
        String answer = INPUT.nextLine();

        if (answer.equalsIgnoreCase("yes")) {
            performAnimalActions(selectedAnimal);
        } else {
            performSadAnimalAction(selectedAnimal);
        }
    }

    private static void performSadAnimalAction(Animal animal) {
        System.out.println();
        System.out.printf("%s is sad.%n", animal.name);
        animal.makeSound();
        animal.sleep();
        System.out.println();
    }

    private static void performAnimalActions(Animal animal) {
        System.out.println();
        animal.eat();
        animal.makeSound();
        System.out.println();
    }
    //  ========================================================================



    //  ==================  SHOP  ===================================
    private static void visitShop() {

    }
    //  ========================================================================
}