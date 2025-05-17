package Controller;

import Model.Farm;
import Model.GhafasiAnimal.Chicken.Chicken;
import Model.GhafasiAnimal.Dinosaur.Dinosaur;
import Model.GhafasiAnimal.Duck.Duck;
import Model.GhafasiAnimal.Rabbit.Rabbit;
import Model.TavileAnimal.Cow.Cow;
import Model.TavileAnimal.Goat.Goat;
import Model.TavileAnimal.Pig.Pig;
import Model.TavileAnimal.Sheep.Sheep;
import Model.User;

public class BuyAnimalController {
    public void BuyAnimalController(String BuildingName , String AnimalName) {
        switch (AnimalName){
            case "Chicken":
                if(Farm.Coops.size() != 0 && Farm.Coops.get(0).size <5) {
                    User.OwnGhafasiAnimals.add(new Chicken());
                    Farm.Coops.get(0).size ++;
                }else {
                    System.out.println("You haven't Coop in your farm");
                }
                break;
            case "Dinosaur":
                if(Farm.Coops.size() != 0 && Farm.Coops.get(0).size <5) {
                    User.OwnGhafasiAnimals.add(new Dinosaur());
                    Farm.Coops.get(0).size ++;
                }else {
                    System.out.println("You haven't Coop in your farm");
                }
                break;
            case "Duck":
                if(Farm.Coops.size() != 0 && Farm.Coops.get(0).size <5) {
                    User.OwnGhafasiAnimals.add(new Duck());
                    Farm.Coops.get(0).size ++;
                }else {
                    System.out.println("You haven't Coop in your farm");
                }
                break;
            case "Rabbit":
                if(Farm.Coops.size() != 0 && Farm.Coops.get(0).size <5) {
                    User.OwnGhafasiAnimals.add(new Rabbit());
                    Farm.Coops.get(0).size ++;
                }else {
                    System.out.println("You haven't Coop in your farm or your Coop is full");
                }
                break;
            case "Cow":
                if(Farm.Barns.size() != 0 && Farm.Barns.get(0).size <5) {
                    User.OwnGhafasiAnimals.add(new Cow());
                    Farm.Barns.get(0).size ++;
                }else{
                    System.out.println("You haven't Barn in your farm or your Barn is full");
                }
                break;
            case "Goat":
                if(Farm.Barns.size() != 0 && Farm.Barns.get(0).size <5) {
                    User.OwnGhafasiAnimals.add(new Goat());
                    Farm.Barns.get(0).size ++;
                }else{
                    System.out.println("You haven't Barn in your farm or your Barn is full");
                }
                break;
            case "Pig":
                if(Farm.Barns.size() != 0 && Farm.Barns.get(0).size <5) {
                    User.OwnGhafasiAnimals.add(new Pig());
                    Farm.Barns.get(0).size ++;
                }else{
                    System.out.println("You haven't Barn in your farm or your Barn is full");
                }
                break;
            case "Sheep":
                if(Farm.Barns.size() != 0 && Farm.Barns.get(0).size <5) {
                    User.OwnGhafasiAnimals.add(new Sheep());
                    Farm.Barns.get(0).size ++;
                }else{
                    System.out.println("You haven't Barn in your farm or your Barn is full");
                }
                break;
        }
    }
}
