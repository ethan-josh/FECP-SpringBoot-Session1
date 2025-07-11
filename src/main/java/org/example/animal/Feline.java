package org.example.animal;

import org.example.Building;

public class Feline extends Animal {
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
        System.out.println(super.name + " is making a feline sound.");
    }

    public static class Tiger extends Feline {
        public Tiger(String name, boolean isHealthy, Building location) {
            super.name = name;
            super.isHealthy = isHealthy;
            super.location = location;
        }
    }

    public static class Lion extends Feline {
        public Lion(String name, boolean isHealthy, Building location) {
            super.name = name;
            super.isHealthy = isHealthy;
            super.location = location;
        }
    }

    public static class Cheetah extends Feline {
        public Cheetah(String name, boolean isHealthy, Building location) {
            super.name = name;
            super.isHealthy = isHealthy;
            super.location = location;
        }
    }
}