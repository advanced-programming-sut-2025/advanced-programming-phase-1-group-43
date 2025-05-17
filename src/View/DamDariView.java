package View;

import Controller.*;
import Controller.SellAnimalController;
import Model.enums.Commmands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DamDariView {
    public void DamDari(String Message) {
        boolean BuildGhafas = Pattern.matches(Message, Commmands.BuildGhafas.getPattern());
        boolean BuyAnimal = Pattern.matches(Message, Commmands.BuyAnimal.getPattern());
        boolean Pet = Pattern.matches(Message, Commmands.Pet.getPattern());
        boolean cheat = Pattern.matches(Message, Commmands.Cheat.getPattern());
        boolean Animal = Pattern.matches(Message, Commmands.Animal.getPattern());
        boolean Shepherd = Pattern.matches(Message, Commmands.Shepherd.getPattern());
        boolean Feed = Pattern.matches(Message, Commmands.Feed.getPattern());
        boolean Produce = Pattern.matches(Message, Commmands.Produce.getPattern());
        boolean SellAnimal = Pattern.matches(Message, Commmands.SellAnimal.getPattern());
        boolean Fishing = Pattern.matches(Message, Commmands.Fishing.getPattern());

        if (BuildGhafas) {
            Pattern pattern = Pattern.compile(Commmands.BuildGhafas.getPattern());
            Matcher matcher = pattern.matcher(Message);
            matcher.find();

            String BuildingName = matcher.group("BuildingName");
            int x = Integer.parseInt(matcher.group("X"));
            int y = Integer.parseInt(matcher.group("Y"));

            Controller.BuildGhafas buildGhafas = new Controller.BuildGhafas();
            buildGhafas.BuildGhafas(BuildingName, x, y);
        }else if (BuyAnimal) {
            Pattern pattern = Pattern.compile(Commmands.BuyAnimal.getPattern());
            Matcher matcher = pattern.matcher(Message);
            matcher.find();

            String BuildingName = matcher.group("Animal");
            String AnimalType = matcher.group("Name");
            Controller.BuyAnimalController buyAnimalController = new Controller.BuyAnimalController();
            buyAnimalController.BuyAnimalController(BuildingName, AnimalType);
        }else if (Pet) {
            Pattern pattern = Pattern.compile(Commmands.Pet.getPattern());
            Matcher matcher = pattern.matcher(Message);
            matcher.find();

            String AnimalName = matcher.group("AnimalName");
            Controller.PetController petController = new Controller.PetController();
            petController.pet(AnimalName);
        }else if (cheat) {
            Pattern pattern = Pattern.compile(Commmands.Cheat.getPattern());
            Matcher matcher = pattern.matcher(Message);
            matcher.find();

            String AnimalName = matcher.group("AnimalName");
            int Count = Integer.parseInt(matcher.group("Count"));
            Count count = new Count();
            count.Count(AnimalName, Count);
        }else if (Animal) {
            ShowAnimal.Show();
        }else if(Shepherd) {
            Pattern pattern = Pattern.compile(Commmands.Shepherd.getPattern());
            Matcher matcher = pattern.matcher(Message);
            matcher.find();

            String AnimalName = matcher.group("AnimalName");
            int X = Integer.parseInt(matcher.group("X"));
            int Y = Integer.parseInt(matcher.group("Y"));
            ShepherdController shepherdController = new ShepherdController();
            shepherdController.shepherd(AnimalName, X, Y);
        } else if (Feed) {
            Pattern pattern = Pattern.compile(Commmands.Feed.getPattern());
            Matcher matcher = pattern.matcher(Message);
            matcher.find();

            String AnimalName = matcher.group("AnimalName");
            FeedController feedController =new FeedController();
            feedController.feed(AnimalName);
        } else if (Produce) {
            Pattern pattern = Pattern.compile(Commmands.Produce.getPattern());
            Matcher matcher = pattern.matcher(Message);
            matcher.find();

            String AnimalName = matcher.group("AnimalName");
            ProduceController produceController = new ProduceController();
            produceController.produce(AnimalName);
        }else if(SellAnimal) {
            Pattern pattern = Pattern.compile(Commmands.SellAnimal.getPattern());
            Matcher matcher = pattern.matcher(Message);
            matcher.find();

            String AnimalName = matcher.group("AnimalName");
            SellAnimalController sellAnimalController = new SellAnimalController();
            sellAnimalController.SellAnimal(AnimalName);
        }else if (Fishing) {
            Pattern pattern = Pattern.compile(Commmands.Fishing.getPattern());
            Matcher matcher = pattern.matcher(Message);
            matcher.find();

            String AnimalName = matcher.group("AnimalName");
        }
    }
}
