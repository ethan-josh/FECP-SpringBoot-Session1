package org.example;

import org.example.animal.Animal;
import org.example.visitor.GuestMain;

import java.util.*;
import java.util.stream.Collectors;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
//        System.out.println("What would you like to do?");
//        System.out.println("1. Visit Enclosure");
//        System.out.println("2. Visit Shop");
//        System.out.println("3. Visit Hospital");
//        System.out.println("4. Leave Zoo");
//        System.out.print("Choose an option: ");

        GuestMain guestMain = new GuestMain();
        guestMain.showMainMenu();
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
                                helper.setUpDone = true;

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
                                if (!helper.setUpDone){
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
                                        // raw name for display:
                                        String enclosureTypeRaw = assignedEnclosure.getSpeciesType();
                                        // lowercase for matching:
                                        String enclosureType    = enclosureTypeRaw.toLowerCase();

                                        System.out.println("\n--- Animal Duty Menu ---");
                                        System.out.println("Animals assigned to you in " + enclosureType + " Enclosure:");

                                        List<Animal> assignedAnimalObjs;
                                        List<String> assignedAnimals;
                                        switch (enclosureType) {
                                            case "pachyderm":
                                                assignedAnimalObjs = helper.getHealthyElephants();
                                                assignedAnimals    = assignedAnimalObjs.stream().map(Animal::getName).collect(Collectors.toList());
                                                break;
                                            case "feline":
                                                assignedAnimalObjs = helper.getHealthyLion();
                                                assignedAnimals    = assignedAnimalObjs.stream().map(Animal::getName).collect(Collectors.toList());
                                                break;
                                            case "bird":
                                                assignedAnimalObjs = helper.getHealthyOwl();
                                                assignedAnimals    = assignedAnimalObjs.stream().map(Animal::getName).collect(Collectors.toList());
                                                break;
                                            default:
                                                assignedAnimalObjs = Collections.emptyList();
                                                assignedAnimals    = Collections.emptyList();
                                        }

                                        if (assignedAnimals.isEmpty()) {
                                            System.out.println("No healthy animals assigned.");
                                            break;
                                        }

                                        for (int i = 0; i < assignedAnimals.size(); i++) {
                                            System.out.println((i + 1) + ". " + assignedAnimals.get(i));
                                        }

                                        System.out.print("\nChoose animal number to interact with (0 to exit): ");
                                        int choice = adminScanner.nextInt();
                                        if (choice <= 0 || choice > assignedAnimalObjs.size()) {
                                            System.out.println("Finished duties for the day.");
                                            break;
                                        }

                                        Animal a = assignedAnimalObjs.get(choice - 1);

                                        System.out.println("\nChoose action:");
                                        System.out.println("1. Feed " + a.getName());
                                        System.out.println("2. Exercise " + a.getName());
                                        System.out.println("3. Examine " + a.getName() + " to Vet");
                                        System.out.print("Choose an option: ");
                                        int action = adminScanner.nextInt();
                                        System.out.println();

                                        switch (action) {
                                            case 1:
                                                System.out.println(a.getName() + " is fed");
                                                break;
                                            case 2:
                                                System.out.println(a.getName() + " is exercised");
                                                break;
                                            case 3:
                                                Hospital hosp = helper.getBuildings()
                                                        .stream()
                                                        .filter(b -> b instanceof Hospital)
                                                        .map(b -> (Hospital) b)
                                                        .findFirst()
                                                        .orElseThrow(() -> new IllegalStateException("No Hospital!"));
                                                a.location = hosp;
                                                a.isHealthy = false;
                                                String ts = LocalDateTime.now()
                                                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                                                System.out.println("Sending to Hospital...");
                                                System.out.println(a.getName() + " admitted at " + ts);
                                                break;
                                            default:
                                                System.out.println("Invalid action. Exiting.");
                                        }
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
                    if (!Helper.getInstance().isZooOpen) {
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