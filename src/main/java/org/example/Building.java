package org.example;

import org.example.animal.*;

public abstract class Building {
    private String location;
    public Building(String buildingType){
        this.location = buildingType;
    }

    public String getLocation() {
        return location;
    }
}

class Hospital extends Building{
    public Hospital () {
        super("Hospital");
    }
}

class Enclosure extends Building{
    private String speciesType;
    public Enclosure (String speciesType) {
        super("Enclosure");
        this.speciesType = speciesType;
    }

    public void setSpeciesType(String speciesType) {
        this.speciesType = speciesType;
    }

    public String getSpeciesType() {
        return speciesType;
    }

    // get the animal's specific enclosure type
    public static Building getAnimalEnclosureType (Animal animal) {
        String enclosure;
        if (animal instanceof Bird)
            enclosure = "Bird";
        else if (animal instanceof Feline)
            enclosure = "Feline";
        else if (animal instanceof Pachyderm)
            enclosure = "Pachyderm";
        else
            enclosure = "";

        return Helper.getInstance().getBuildings()
                .stream()
                .filter(b -> b instanceof Enclosure && ((Enclosure) b).getSpeciesType().equalsIgnoreCase(enclosure))
                .findFirst().orElseThrow(() -> new IllegalStateException("No Enclosure!"));
    }
}

abstract class Shop extends Building{
    public Shop (String buildingType) {
        super(buildingType);
    }
}

class Ticket extends Shop{
    public Ticket () {
        super("Ticket");
    }
}

class Drink extends Shop{
    public Drink () {
        super("Drink");
    }
}

class Food extends Shop{
    public Food () {
        super("Food");
    }
}

class Gift extends Shop{
    public Gift () {
        super("Gift");
    }
}
