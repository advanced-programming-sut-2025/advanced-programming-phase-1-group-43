package Model.PlantAndCrops;

import Model.Plant;
import Model.enums.PlantType;

public class Blueberry extends Plant {
    public String plantType = "Blueberry";
    public int XPlanting = Integer.parseInt(null);
    public int YPlanting = Integer.parseInt(null);
    public int totalHarvestTime = 13;
    public int BaseSellPrice = 50;
    public int RegrowthTime = 4;
    public boolean idEdible = true;
    public int Energy = 25;
    public int Age = 0;
    public String Season = "Summer";
    public boolean CanBecomeGiantPlant = false;

    public Blueberry(String plantType, int XPlanting, int YPlanting) {
        super(plantType, XPlanting, YPlanting);
    }
    public void getInfo() {
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