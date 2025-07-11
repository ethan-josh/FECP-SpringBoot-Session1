package org.example.animal;

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
        System.out.println(super.name + " is making a sound.");
    }

    public static class Parrot extends Bird {
        public Parrot(boolean isHealthy, String location) {
            super.name = "Parrot";
            super.isHealthy = isHealthy;
            super.location = location;
        }
    }

    public static class Falcon extends Bird {
        public Falcon(boolean isHealthy, String location) {
            super.name = "Falcon";
            super.isHealthy = isHealthy;
            super.location = location;
        }
    }

    public static class Owl extends Bird {
        public Owl(boolean isHealthy, String location) {
            super.name = "Owl";
            super.isHealthy = isHealthy;
            super.location = location;
        }
    }
}