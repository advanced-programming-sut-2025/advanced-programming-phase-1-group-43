package Model.PlantAndCrops;

import Model.Plant;
import Model.enums.PlantType;

public class Melon extends Plant {
    public String plantType = "Melon";
    public int XPlanting = Integer.parseInt(null);
    public int YPlanting = Integer.parseInt(null);
    public int totalHarvestTime = 12;
    public int BaseSellPrice = 250;
    public int RegrowthTime = 0;
    public boolean idEdible = true;
    public int Energy = 113;
    public int Age = 0;
    public String Season = "Summer";
    public boolean CanBecomeGiantPlant = true;

    public Melon(String plantType, int XPlanting, int YPlanting) {
        super(plantType, XPlanting, YPlanting);
    }
    public static void getInfo() {
        System.out.println("Name: " + this.plantType);
        System.out.println("Source: " + this.plantType + " Seeds");
        System.out.println("Stages: 1-1-1");
        System.out.println("Total Harvest Time: " + this.totalHarvestTime);
        if (this.RegrowthTime != 0) {
            System.out.println("Regrowth Time: " + this.RegrowthTime);
        }else {
            System.out.println("Regrowth Time: ");
        }
        System.out.println("Base Sell Price: " + this.BaseSellPrice);
        if (this.idEdible) {
            System.out.println("Is Edible: " + this.idEdible);
            System.out.println("Base Energy: " + this.Energy);
            System.out.println("Base Health: " + 33);
        }
        System.out.println("Season: " + this.Season);
        System.out.println("Can Become Giant: " + this.CanBecomeGiantPlant);
    }

}
