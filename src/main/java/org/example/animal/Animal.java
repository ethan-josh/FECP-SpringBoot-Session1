package org.example.animal;

import org.example.Building;

public abstract class Animal {
    public boolean isHealthy;
    protected String name;
    protected Building location;

    public abstract void eat();
    public abstract void sleep();
    public abstract void roam();
    public abstract void makeSound();
}
