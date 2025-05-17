package Controller;

import Model.User;

public class Count {
    public void Count(String AnimalName, int Amount) {
        for(Object Animal: User.OwnGhafasiAnimals){
            if(AnimalName.equals(Animal.getClass().getName())){
                Animal.Love  += Count;
            }
        }
        for(Object Animal: User.OwnTavileiiAnimals){
            if(AnimalName.equals(Animal.getClass().getName())){
                Animal.Love  += Count;
            }
        }
    }
}
