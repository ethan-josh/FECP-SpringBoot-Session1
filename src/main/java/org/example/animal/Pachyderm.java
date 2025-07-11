package org.example.animal;

import org.example.Building;

public class Pachyderm extends Animal {
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
        System.out.println(super.name + " is making a pachyderm sound.");
    }

    public static class Hippo extends Pachyderm {
        public Hippo(String name, boolean isHealthy, Building location) {
            super.name = name;
            super.isHealthy = isHealthy;
            super.location = location;
        }
    }

    public static class Rhino extends Pachyderm {
        public Rhino(String name, boolean isHealthy, Building location) {
            super.name = name;
            super.isHealthy = isHealthy;
            super.location = location;
        }
    }

    public static class Elephant extends Pachyderm {
        public Elephant(String name, boolean isHealthy, Building location) {
            super.name = name;
            super.isHealthy = isHealthy;
            super.location = location;
        }
    }
}