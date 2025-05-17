package Controller;

import Model.User;

public class ShowAnimal {
    public static void Show(){
        for(Object Animal : User.OwnGhafasiAnimals){
            System.out.println(Animal.toString());
        }
        for(Object Animal : User.OwnTavileiiAnimals){
            System.out.println(Animal.toString());
        }
    }
}
