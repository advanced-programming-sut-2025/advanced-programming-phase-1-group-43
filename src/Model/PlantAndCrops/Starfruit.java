package Model.PlantAndCrops;

import Model.Plant;
import Model.enums.PlantType;

public class Starfruit extends Plant {
    public String plantType = "Starfruit";
    public int XPlanting = Integer.parseInt(null);
    public int YPlanting = Integer.parseInt(null);
    public int totalHarvestTime = 13;
    public int BaseSellPrice = 750;
    public int RegrowthTime = 0;
    public boolean idEdible = true;
    public int Energy = 125;
    public int Age = 0;
    public String Season = "Summer";
    public boolean CanBecomeGiantPlant = false;

    public Starfruit(String plantType, int XPlanting, int YPlanting) {
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
