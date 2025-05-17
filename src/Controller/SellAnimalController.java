package Controller;

import Model.User;

public class SellAnimalController {
    public void SellAnimal(String AnimalName) {
        for (Object Animal : User.OwnGhafasiAnimals){
            if(Animal.getClass().getName().equals(AnimalName)){
                System.out.println("Sell Successfully");
            }
        }
        for (Object Animal : User.OwnTavileiiAnimals){
            if(Animal.getClass().getName().equals(AnimalName)){
                System.out.println("Sell Successfully");
            }
        }
    }
}
