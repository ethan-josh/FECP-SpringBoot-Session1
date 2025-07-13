package org.example;

import org.example.animal.Animal;

import java.time.LocalDateTime;
import java.util.List;

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
        private int age;

        public Visitors(String name, Building initLocation, int age) {
            super(name, initLocation);
            this.age = age;
        }

        public int getAge() {
            return age;
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

        public void lecture() {
            System.out.println("Dr. " + getName() + " gives a science lecture on animal health and conservation." );
        }

        public void heal(List<Animal> sickAnimals) {
            if (sickAnimals.isEmpty())
                System.out.println("There are no sick animals. All animals are healthy");
            else {
                System.out.println("Dr. " + getName() + " begins healing sick animals..." );
                for(Animal a : sickAnimals) {
                    System.out.println("✅ Healed: " + a.getName());
                    // make animal healthy
                    a.isHealthy = true;
                    System.out.println(a.getName() + " has been discharged and return to enclosure.");
                    // set animal's location to their proper enclosure
                    a.location = Enclosure.getAnimalEnclosureType(a);
                    LocalDateTime currentDateTime = LocalDateTime.now();
                    // remove old record of animal
                    Helper.getInstance().healedAnimals.remove(a);
                    // logs cured animals along with their timestamp in a map
                    Helper.getInstance().healedAnimals.put(a, currentDateTime);
                }
            }
        }
    }


}
