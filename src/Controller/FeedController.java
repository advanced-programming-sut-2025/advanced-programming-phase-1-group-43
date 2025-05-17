package Controller;

import Model.User;

public class FeedController {
    public void feed(String AnimalName) {
        for (Object Animal:User.OwnGhafasiAnimals){
            if(Animal.getClass().getName().equals(AnimalName)){
                //Animal.getClass().health ++;
                System.out.println(AnimalName+" Feed Successfully");
            }
        }
        for (Object Animal:User.OwnTavileiiAnimals) {
            if (Animal.getClass().getName().equals(AnimalName)) {
                //Animal.getClass().health ++;
                System.out.println(AnimalName + " Feed Successfully");
            }
        }
        }
}
