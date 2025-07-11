package org.example.animal;

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
        System.out.println(super.name + " is making a sound.");
    }

    public static class Hippo extends Pachyderm {
        public Hippo(boolean isHealthy, String location) {
            super.name = "Hippo";
            super.isHealthy = isHealthy;
            super.location = location;
        }
    }

    public static class Rhino extends Pachyderm {
        public Rhino(boolean isHealthy, String location) {
            super.name = "Rhino";
            super.isHealthy = isHealthy;
            super.location = location;
        }
    }

    public static class Elephant extends Pachyderm {
        public Elephant(boolean isHealthy, String location) {
            super.name = "Elephant";
            super.isHealthy = isHealthy;
            super.location = location;
        }
    }
}