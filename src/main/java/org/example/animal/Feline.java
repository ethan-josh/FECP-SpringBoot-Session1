package org.example.animal;

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
        System.out.println(super.name + " is making a sound.");
    }

    public static class Tiger extends Feline {
        public Tiger(boolean isHealthy, String location) {
            super.name = "Tiger";
            super.isHealthy = isHealthy;
            super.location = location;
        }
    }

    public static class Lion extends Feline {
        public Lion(boolean isHealthy, String location) {
            super.name = "Lion";
            super.isHealthy = isHealthy;
            super.location = location;
        }
    }

    public static class Cheetah extends Feline {
        public Cheetah(boolean isHealthy, String location) {
            super.name = "Cheetah";
            super.isHealthy = isHealthy;
            super.location = location;
        }
    }
}