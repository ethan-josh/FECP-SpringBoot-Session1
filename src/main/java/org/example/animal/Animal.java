package org.example.animal;

public abstract class Animal {
    protected boolean isHealthy;
    protected String name;
    protected String location;

    public abstract void eat();
    public abstract void sleep();
    public abstract void roam();
    public abstract void makeSound();
}
