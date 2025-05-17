package Controller;

import Model.App;
import Model.Farm;
import Model.PlantAndCrops.*;
import Model.plant;

public class ShowPlant {
    public void ShowPlant(int x,int y){
        boolean isFound = false;
        for(plant Plant: Farm.AllPlants){
            if (Plant.XPlanting == x && Plant.YPlanting == y){
                isFound = true;
                switch (Plant.plantType) {
                    case "Beet": Beet.getInfo(); break;
                    case "Amaranth": Amaranth.getInfo(); break;
                    case "Ancient Fruit": AncientFruit.getInfo(); break;
                    case "Artichoke": Artichoke.getInfo(); break;
                    case "Blue Jazz": BlueJazz.getInfo(); break;
                    case "Blueberry": Blueberry.getInfo(); break;
                    case "Bok Choy": BokChoy.getInfo(); break;
                    case "Broccoli": Broccoli.getInfo(); break;
                    case "Carrot": Carrot.getInfo(); break;
                    case "Cauliflower": Cauliflower.getInfo(); break;
                    case "Coffee Bean": CoffeeBean.getInfo(); break;
                    case "Corn": Corn.getInfo(); break;
                    case "Cranberries": Cranberries.getInfo(); break;
                    case "Eggplant": Eggplant.getInfo(); break;
                    case "Fairy Rose": FairyRose.getInfo(); break;
                    case "Garlic": Garlic.getInfo(); break;
                    case "Grape": Grape.getInfo(); break;
                    case "Hot Pepper": HotPepper.getInfo(); break;
                    case "Hops": Hops.getInfo(); break;
                    case "Kale": Kale.getInfo(); break;
                    case "Melon": Melon.getInfo(); break;
                    case "Parsnip": Parsnip.getInfo(); break;
                    case "Poppy": Poppy.getInfo(); break;
                    case "Potato": Potato.getInfo(); break;
                    case "Pumpkin": Pumpkin.getInfo(); break;
                    case "Radish": Radish.getInfo(); break;
                    case "Red Cabbage": RedCabbage.getInfo(); break;
                    case "Rice": UnmilledRice.getInfo(); break;
                    case "Rhubarb": Rhubarb.getInfo(); break;
                    case "Starfruit": Starfruit.getInfo(); break;
                    case "Strawberry": Strawberry.getInfo(); break;
                    case "Summer Spangle": SummerSpangle.getInfo(); break;
                    case "Summer Squash": SummerSquash.getInfo(); break;
                    case "Sunflower": Sunflower.getInfo(); break;
                    case "Tomato": Tomato.getInfo(); break;
                    case "Tulip": Tulip.getInfo(); break;
                    case "Wheat": Wheat.getInfo(); break;
                    case "Yam": Yam.getInfo(); break;
                    case "Powdermelon": Powdermelon.getInfo(); break;
                    default: System.out.println("Not Plant There"); break;
                }
            }
        }
        if (!isFound){
            System.out.println("Plant not found");
        }
    }
}
