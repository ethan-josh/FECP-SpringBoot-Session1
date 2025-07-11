package org.example.guest;

import org.example.Helper;
import org.example.animal.Animal;
import org.example.animal.Bird;
import org.example.animal.Feline;
import org.example.animal.Pachyderm;

import java.util.List;
import java.util.Scanner;

public class GuestMain {
    private static final Scanner input = new Scanner(System.in);
    private static final Helper helper = Helper.getInstance();

    public static void main(String[] args) {
        displayOptions();
    }

    public static void displayOptions() {
        System.out.println("What would you like to do?");
        System.out.println("1. Visit Enclosure");
        System.out.println("2. Visit Shop");
        System.out.println("3. Visit Hospital");
        System.out.println("4. Leave Zoo");
        System.out.print("Choose an option: ");
        int inputtedOption = Integer.parseInt(input.nextLine());

        switch (inputtedOption) {
            case 1 -> visitEnclosure();
        }
    }

    private static void visitEnclosure() {
        System.out.println("\n=== Zoo Enclosure ===");
        System.out.println("Choose Enclosure:");
        System.out.println("1. Pachyderm");
        System.out.println("2. Feline");
        System.out.println("3. Bird");
        System.out.print("Choose an option: ");
        int inputtedOption = Integer.parseInt(input.nextLine());

        displayAnimalListFromSpecies(getAnimalInstance(inputtedOption), inputtedOption);
    }

    private static void displayAnimalListFromSpecies(Animal animal, int option) {
        switch (option) {
            case 1 -> {
                System.out.println("\n=== Pachyderm ===");
                handleAnimalList(helper.elephantList, animal);
            }
            case 2 -> {
                System.out.println("\n=== Feline ===");
                handleAnimalList(helper.lionList, animal);
            }
            case 3 -> {
                System.out.println("\n=== Bird ===");
                handleAnimalList(helper.owlList, animal);
            }
        }
    }

    private static void handleAnimalList(List<String> animalList, Animal animal) {
        for (int i = 0; i < animalList.size(); i++) {
            String animalName = animalList.get(i);
            System.out.println((i + 1) + ". " + animalName);
        }

        System.out.print("Choose an option: ");
        int chosenAnimalInt = Integer.parseInt(input.nextLine());

        String chosenAnimal = animalList.get(chosenAnimalInt - 1);
        System.out.print("\nWould you like to feed the " + chosenAnimal + " (yes/no): ");
        String answer = input.nextLine();

        System.out.println();
        if (answer.equalsIgnoreCase("yes")) {
            performAnimalActions(animal, chosenAnimal);
        }
    }

    private static void performAnimalActions(Animal animal, String chosenAnimal) {
        if (animal instanceof Pachyderm pachyderm) {
            pachyderm.setName(chosenAnimal);
            pachyderm.eat();
            pachyderm.makeSound();
        } else if (animal instanceof Feline feline) {
            feline.setName(chosenAnimal);
            feline.eat();
            feline.makeSound();
        } else if (animal instanceof Bird bird) {
            bird.setName(chosenAnimal);
            bird.eat();
            bird.makeSound();
        }
    }

    private static Animal getAnimalInstance(int inputtedOption) {
        switch (inputtedOption) {
            case 1 -> {
                return new Pachyderm();
            }
            case 2 -> {
                return new Feline();
            }
            case 3 -> {
                return new Bird();
            }
        };

        return null;
    }
}