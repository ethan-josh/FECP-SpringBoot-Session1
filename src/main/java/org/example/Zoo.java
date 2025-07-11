package org.example;

import java.util.Scanner;

public class Zoo {
    public static void logInMenu() {
        System.out.println("=== Welcome to Zoo Admin Console ===");
        System.out.println("Please log in.");
    }

    public static void printAdminMenu() {
        System.out.println("========== 🦁 ZOO ADMIN MAIN MENU ==========");
        System.out.println("1. Setup Zoo Staff");
        System.out.println("2. Access Handler Module");
        System.out.println("3. Open Zoo to Visitors");
        System.out.println("4. Close Zoo to Visitors");
        System.out.println("5. Exit");
        System.out.print("Choose an option: ");
    }

    public static void printTicketMenu(){
        System.out.println("=== 🎟️ WELCOME TO THE ZOO TICKET SHOP ===");
        System.out.println("Here's what you can experience in the zoo:");
        System.out.println("Visit Animal Enclosures (Elephant, Lion, Owl)");
        System.out.println("Buy snacks and drinks from our Shops");
        System.out.println("Listen to science lectures at the Hospital");
        System.out.println("Buy fun gifts at our Gift Shop");
        System.out.println();
        System.out.print("Would you like to buy a ticket? (yes/no): ");
    }
    public static void printVisitorMenu(){
        System.out.println("What would you like to do?");
        System.out.println("1. Visit Enclosure");
        System.out.println("2. Visit Shop");
        System.out.println("3. Visit Hospital");
        System.out.println("4. Leave Zoo");
        System.out.print("Choose an option: ");
    }

    public static void initUserChoice() {
        System.out.println("=== Welcome to the Zoo Simulation === ");
        System.out.println("1. Simulate as Admin");
        System.out.println("2. Simulate as Visitor");
        System.out.println("3. Exit");
        System.out.print("Choose an option: ");
    }
    public static void main(String[] args) {
        // Insert codes here
        Scanner initChoice = new Scanner(System.in);
        int initOption = 0;

        while (initOption != 3){
            initUserChoice();
            initOption = initChoice.nextInt();

            switch(initOption){
                case 1: // Go as Admin
                    Scanner adminScanner = new Scanner(System.in);
                    int adminOption = 0;
                    logInMenu();
                    System.out.print("Enter username: ");
                    // Add validation
                    System.out.print("Enter password: ");
                    // Add validation

                    printAdminMenu();
                    break;

                case 2: // Go as Visitor
                    Scanner visitorScanner = new Scanner(System.in);
                    int visitorOption = 0;

                    printTicketMenu();
                    break;

                case 3: // Exit
                    break;
                default:
                    System.out.println("Enter a valid number from 1 to 3.");
                    break;
            }
        }



    }
}