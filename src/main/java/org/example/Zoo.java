package org.example;

import java.util.Random;
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

    public static boolean hasTicket(Scanner ticketScanner) {
        String buyTicketOption = ticketScanner.nextLine();
        // buy new ticket
        if (buyTicketOption.equalsIgnoreCase("yes")) {
            System.out.print("Enter your name: ");
            String name = ticketScanner.nextLine();
            System.out.print("Enter your age: ");
            int age = ticketScanner.nextInt();
            ticketScanner.nextLine();
            Person.Visitors visitor = new Person.Visitors(name, null, age);
            //Print ticket type and price
            printTicketDetail(visitor);
            String confirmPurchase = ticketScanner.nextLine();
            // confirmation before generating the ticket
            if (confirmPurchase.equalsIgnoreCase("yes")) {
                Helper.getInstance().addPerson(visitor);
                System.out.println("Ticket purchased!");
                System.out.println("Your ticket code is: " + generateTicket());
                System.out.println("[Ticket added to system]");
                return true;
            }

            //use an existing ticket
        } else {
            System.out.print("Do you have an existing ticket? (yes/no): ");
            String confirmTicket= ticketScanner.nextLine();
            return confirmTicket.equalsIgnoreCase("yes");
        }
        return false;
    }

    public static void printTicketDetail(Person.Visitors visitor) {
        //Display ticket classification and pricing based on visitor's age
        if(visitor.getAge() >= 0 && visitor.getAge() <= 5) {
            System.out.println("You are qualify for a CHILD ticket.");
            System.out.println("Ticket Price: ₱0.00");

        } else if (visitor.getAge() >= 6 && visitor.getAge() <= 17) {
            System.out.println("You are qualify for a STUDENT ticket.");
            System.out.println("Ticket Price: ₱75.00");

        } else if (visitor.getAge() >= 18 && visitor.getAge() <= 59) {
            System.out.println("You are qualify for a ADULT ticket.");
            System.out.println("Ticket Price: ₱150.00");

        } else if (visitor.getAge() >= 60) {
            System.out.println("You are qualify for a SENIOR ticket.");
            System.out.println("Ticket Price: ₱50.00");

        }
        System.out.print("Proceed with purchase? (yes/no): ");
    }

    public static String generateTicket() {
        // initialize ticket prefix
        StringBuilder sb = new StringBuilder("ZOO-");
        Random random = new Random();
        String generatedTicket;
        while(true) {
            //generate a random number suffix
            String randomNumber = String.valueOf(random.nextInt(1000));
            sb.append("0".repeat(4-randomNumber.length()));
            generatedTicket = sb.append(randomNumber).toString();
            // check if generated ticket number exists in the list of ticket
            if(!Helper.getInstance().searchTicket(generatedTicket))
                break;
            // reset the number
            sb.delete(4, 4+sb.length());
        }
        // add generated ticket to list
        Helper.getInstance().addTickets(generatedTicket);
        return generatedTicket;
    }
    public static boolean validateTicket(Scanner ticketScanner) {
        System.out.println("=== Visitor Entry ===");
        System.out.print("Enter your ticket code: ");
        String ticket = ticketScanner.nextLine();
        //validate ticket
        while(!ticket.equalsIgnoreCase("q")) {
            if(Helper.getInstance().searchTicket(ticket)){
                return true;
            }
            System.out.print("Please input a valid ticket (Type 'q' to exit): ");
            ticket = ticketScanner.nextLine();
        }
        return false;
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
                    if (Helper.getInstance().isZooOpen) {
                        System.out.println("Zoo is closed. Please come back next time");
                    } else {
                        Scanner visitorScanner = new Scanner(System.in);
                        int visitorOption = 0;

                        printTicketMenu();

                        Scanner ticketScanner = new Scanner(System.in);
                        boolean proceedTicketValidation = false;
                        // Buy new ticket or use an existing one
                        proceedTicketValidation = hasTicket(ticketScanner);

                        if (proceedTicketValidation) {
                            // validate visitor's ticket
                            boolean isValidTicket = validateTicket(ticketScanner);

                            if(isValidTicket){
                                printVisitorMenu();
                                // Insert codes here for visitor actions
                            }

                        }
                    }
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