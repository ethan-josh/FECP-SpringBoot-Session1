package org.example.animal;

import org.example.Building;

public class Bird extends Animal {
    @Override
    public void eat() {
        System.out.println(super.name + " is eating.");
    }

    @Override
    public void sleep() {
        System.out.println(super.name + " is sleeping.");
    }

    @Override
    public void roam() {
        System.out.println(super.name + " is roaming.");
    }

    @Override
    public void makeSound() {
        System.out.println(super.name + " is making a bird sound.");
    }


    public static class Parrot extends Bird {
        public Parrot(String name, boolean isHealthy, Building location) {
            super.name = name;
            super.isHealthy = isHealthy;
            super.location = location;
        }
    }

    public static class Falcon extends Bird {
        public Falcon(String name, boolean isHealthy, Building location) {
            super.name = name;
            super.isHealthy = isHealthy;
            super.location = location;
        }
    }

    public static class Owl extends Bird {
        public Owl(String name, boolean isHealthy, Building location) {
            super.name = name;
            super.isHealthy = isHealthy;
            super.location = location;
        }
    }
}