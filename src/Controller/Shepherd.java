package Controller;

import Model.User;

public class Shepherd {
    public void Shepherd(String AnimalNAme,int X,int Y) {
        for(Object animal: User.OwnGhafasiAnimals){
            if(Object.class.getName().equals(AnimalNAme)){
                System.out.println("Shepherd Animal successfully");
                return;
            }
        }
        System.out.println("Animal not found");
    }
}
