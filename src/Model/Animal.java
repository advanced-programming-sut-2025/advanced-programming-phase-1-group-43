package Model;

import Model.enums.AnimalType;

public class Animal {
    private AnimalType animalType;
    private int hunger;
    private int health;

    public Item produceProduct() {
        return new Item();
    }
}
