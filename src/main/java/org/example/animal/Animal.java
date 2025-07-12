package org.example.animal;

import org.example.Building;

public abstract class Animal {
    public boolean isHealthy;
    public String name;
    public Building location;

    public abstract void eat();
    public abstract void sleep();
    public abstract void roam();
    public abstract void makeSound();
}
