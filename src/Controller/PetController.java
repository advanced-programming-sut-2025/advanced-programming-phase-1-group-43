package Controller;

import Model.TavileAnimal.Cow.Cow;
import Model.User;

public class PetController {
    public void pet(String animalName) {
        for (Object ghafasiAnimals:User.OwnGhafasiAnimals){
            if(ghafasiAnimals.getClass().getName().equals(animalName)){
                ghafasiAnimals.Love +=15;
            }
        }
    }
}
