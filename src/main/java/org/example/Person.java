package org.example;

public abstract class Person {
    private String name;
    private Building location;

    public Person(String name, Building initLocation) {
        this.name = name;
        this.location = initLocation;
    }
    // Generate getters
    public String getName() {
        return name;
    }
    public Building getLocation() {
        return location;
    }
    // Generate setters
    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(Building location) {
        this.location = location;
    }

    public void goTo(Building destination){
        System.out.println("Going to " + destination);
    }

    // ***** Visitor *****
    public static class Visitors extends Person {
        public Visitors(String name, Building initLocation) {
            super(name, initLocation);
        }

    }

    // ***** Manager *****
    public static class Managers extends Person {
        public Managers(String name, Building initLocation) {
            super(name, initLocation);
        }
    }

    // ***** Vendors *****
    public static class Vendors extends Person {
        public Vendors(String name, Building initLocation) {
            super(name, initLocation);
        }
    }

    // ***** Handlers *****
    public static class Handlers extends Person {
        public Handlers(String name, Building initLocation) {
            super(name, initLocation);
        }
    }

    // ***** Veterinarians *****
    public static class Veterinarians extends Person {
        public Veterinarians(String name, Building initLocation) {
            super(name, initLocation);
        }
    }


}
