package org.example;

import java.util.Objects;
import java.util.Scanner;
import java.util.*;

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
        Helper helper = Helper.getInstance();

        // Create buildings
        Hospital hospital = new Hospital();
        Ticket ticketShop = new Ticket();
        Food foodShop = new Food();
        Gift giftShop = new Gift();
        Drink drinkShop = new Drink();
        Enclosure pachydermEnclosure = new Enclosure("Pachyderm");
        Enclosure felineEnclosure = new Enclosure("Feline");
        Enclosure birdEnclosure = new Enclosure("Bird");


        // Add buildings to zoo
        helper.addBuilding(hospital);
        helper.addBuilding(ticketShop);
        helper.addBuilding(foodShop);
        helper.addBuilding(giftShop);
        helper.addBuilding(drinkShop);
        helper.addBuilding(pachydermEnclosure);
        helper.addBuilding(felineEnclosure);
        helper.addBuilding(birdEnclosure);

        // Insert codes here
        Scanner initChoice = new Scanner(System.in);
        int initOption = 0;

        while (initOption != 3){
            initUserChoice();
            initOption = initChoice.nextInt();

            switch(initOption){
                case 1: // Go as Admin
                    Scanner adminScanner = new Scanner(System.in);
                    // Admin validation hardcoded
                    String userName = "admin";
                    String passWord = "adminadmin";

                    int adminOption = 0;

                    logInMenu();
                    System.out.print("Enter username: ");
                    String userNameInput = adminScanner.next();
                    if (!Objects.equals(userNameInput, userName)){
                        System.out.println("Wrong username. Exiting simulation...");
                        initOption = 4;
                        break;
                    }else{
                        System.out.print("Enter password: ");
                        String passWordInput = adminScanner.next();
                        if (!Objects.equals(passWordInput, passWord)){
                            System.out.println("Wrong password. Exiting simulation...");
                            initOption = 4;
                            break;
                        }
                    }
                    int adminAction = 0;
                    boolean setUpDone = false;
                    while (adminAction != 5){
                        printAdminMenu();
                        adminAction = adminScanner.nextInt();
                        switch (adminAction){
                            case 1: // setup zoo staff
                                System.out.println("--- Zoo Setup ---");
                                System.out.print("Enter your name, Manager: ");
                                String managerName = adminScanner.next();
                                System.out.print("Enter Veterinarians's name: ");
                                String vetName = adminScanner.next();
                                System.out.print("Enter Handler for Pachyderm Enclosure: ");
                                String pachydermHandlerName = adminScanner.next();
                                System.out.print("Enter Handler for Feline Enclosure: ");
                                String felineHandlerName = adminScanner.next();
                                System.out.print("Enter Handler for Bird Enclosure: ");
                                String birdHandlerName = adminScanner.next();
                                System.out.print("Enter Vendor for Ticket Shop: ");
                                String ticketVendorName = adminScanner.next();
                                System.out.print("Enter Vendor for Food Shop: ");
                                String foodVendorName = adminScanner.next();
                                System.out.print("Enter Vendor for Drink Shop: ");
                                String drinkVendorName = adminScanner.next();
                                System.out.print("Enter Vendor for Gift Shop: ");
                                String giftVendorName = adminScanner.next();
                                System.out.println("Zoo staff setup complete.");
                                setUpDone = true;

                                helper.addPerson(new Person.Managers(managerName, null));
                                helper.addPerson(new Person.Veterinarians(vetName, hospital));
                                helper.addPerson(new Person.Handlers(pachydermHandlerName, pachydermEnclosure));
                                helper.addPerson(new Person.Handlers(felineHandlerName, felineEnclosure));
                                helper.addPerson(new Person.Handlers(birdHandlerName, birdEnclosure));
                                helper.addPerson(new Person.Vendors(ticketVendorName, ticketShop));
                                helper.addPerson(new Person.Vendors(foodVendorName, foodShop));
                                helper.addPerson(new Person.Vendors(drinkVendorName, drinkShop));
                                helper.addPerson(new Person.Vendors(giftVendorName, giftShop));
                                break;
                            case 2: // access handler module
                                if (!setUpDone){
                                    System.out.println("Please setup Zoo staff first.");
                                    break;
                                }

                                System.out.print("Enter your name (Handler): ");
                                String handlerName = adminScanner.next();
                                boolean found = false;

                                for (Person p : helper.getPerson()) {
                                    if (p instanceof Person.Handlers && p.getName().equalsIgnoreCase(handlerName)) {
                                        found = true;
                                        Enclosure assignedEnclosure = (Enclosure) p.getLocation();
                                        String enclosureType = assignedEnclosure.getSpeciesType();

                                        System.out.println("--- Animal Duty Menu ---");
                                        System.out.println("Animals assigned to you in " + enclosureType + " Enclosure:");

                                        ArrayList<String> assignedAnimals = new ArrayList<>();
                                        switch (enclosureType.toLowerCase()) {
                                            case "pachyderm":
                                                assignedAnimals = helper.elephantList;
                                                break;
                                            case "feline":
                                                assignedAnimals = helper.lionList;
                                                break;
                                            case "bird":
                                                assignedAnimals = helper.owlList;
                                                break;
                                            default:
                                                System.out.println("No animals found.");
                                        }

                                        for (int i = 0; i < assignedAnimals.size(); i++) {
                                            System.out.println((i + 1) + ". " + assignedAnimals.get(i));
                                        }

                                        System.out.print("Choose animal to interact with (0 to exit): ");
                                        int choice = adminScanner.nextInt();
                                        if (choice > 0 && choice <= assignedAnimals.size()) {
                                            System.out.println("Interacting with " + assignedAnimals.get(choice - 1) + "...");
                                        } else {
                                            System.out.println("Exiting handler module.");
                                        }
                                        break;
                                    }
                                }

                                if (!found) {
                                    System.out.println("Handler not found or not assigned.");
                                }

                                break;
                            case 3: // open zoo
                                if (!helper.isZooOpen) {
                                    helper.isZooOpen = true;
                                    System.out.println("The Zoo is now open!");
                                } else {
                                    System.out.println("The Zoo is already open.");
                                }
                                break;

                            case 4: // close zoo
                                if (helper.isZooOpen) {
                                    helper.isZooOpen = false;
                                    System.out.println("The Zoo is now closed.");
                                } else {
                                    System.out.println("The Zoo is already closed.");
                                }
                                break;

                            case 5: // exit
                                break;
                        }
                    }


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