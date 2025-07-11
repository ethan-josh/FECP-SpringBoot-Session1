package org.example;

import org.example.animal.Animal;
import org.example.animal.Bird;
import org.example.animal.Feline;
import org.example.animal.Pachyderm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class Helper {
    //===================   FOR SINGLETON CONFIGURATIONS   ================
    private static volatile boolean isInstanceCreated = false;

    private Helper() {
        if (!isInstanceCreated) isInstanceCreated = true;
    }

    private static class HelperHolder {
        private static final Helper INSTANCE = new Helper();
    }

    public static Helper getInstance() {
        return HelperHolder.INSTANCE;
    }
    //======================================================================

    //  list of animal
    public final ArrayList<Animal> animals = new ArrayList<>(
            Arrays.asList(
                    new Feline.Lion("Simba", true, new Enclosure("Feline")),
                    new Feline.Lion("Mufasa", true, new Enclosure("Feline")),
                    new Bird.Owl("Hedwig", true, new Enclosure("Bird")),
                    new Bird.Owl("Big Mama", true, new Enclosure("Bird")),
                    new Pachyderm.Elephant("Tantor", true, new Enclosure("Pachyderm")),
                    new Pachyderm.Elephant("Dumbo", true, new Enclosure("Pachyderm"))
            )
    );

    public List<Animal> getHealthyElephants() {
        return animals.stream()
                .filter(a -> a instanceof Pachyderm.Elephant && a.isHealthy)
                .collect(Collectors.toList());
    }

    public List<Animal> getHealthyOwl() {
        return animals.stream()
                .filter(a -> a instanceof Bird.Owl && a.isHealthy)
                .collect(Collectors.toList());
    }

    public List<Animal> getHealthyLion() {
        return animals.stream()
                .filter(a -> a instanceof Feline.Lion && a.isHealthy)
                .collect(Collectors.toList());
    }

    public boolean isZooOpen = false;
    private final ArrayList<String> tickets = new ArrayList<>();
    private final ArrayList<Building> buildings = new ArrayList<>();
    private final ArrayList<Person> person = new ArrayList<>();

    //  get all the tickets
    public ArrayList<String> getTickets() {
        return this.tickets;
    }

    //  add a ticket
    public void addTickets(String ticket) {
        this.tickets.add(ticket);
    }

    //  search a ticket if it exists
    public boolean searchTicket(String ticket) {
        return this.tickets.contains(ticket);
    }

    //  add building?
    public void addBuilding(Building building) {
        this.buildings.add(building);
    }

    //  get all list of buildings
    public ArrayList<Building> getBuildings() {
        return this.buildings;
    }

    //  add a person
    public void addPerson(Person person) {
        this.person.add(person);
    }

    //  get list of persons
    public ArrayList<Person> getPerson() {
        return this.person;
    }
}