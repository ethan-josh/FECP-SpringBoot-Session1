package org.example;

import org.example.animal.Animal;

import java.util.ArrayList;
import java.util.Arrays;

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

    //  list of animals
    public final ArrayList<String> lionList = new ArrayList<>(Arrays.asList("Simba", "Mufasa"));
    public final ArrayList<String> owlList = new ArrayList<>(Arrays.asList("Hedwig", "Big Mama"));
    public final ArrayList<String> elephantList = new ArrayList<>(Arrays.asList("Tantor", "Dumbo"));

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