package Model.PlantAndCrops;

import Model.Plant;
import Model.enums.PlantType;

public class Cranberries extends Plant {
    public String plantType = "Cranberries";
    public int XPlanting = Integer.parseInt(null);
    public int YPlanting = Integer.parseInt(null);
    public int totalHarvestTime = 7;
    public int BaseSellPrice = 75;
    public int RegrowthTime = 5;
    public boolean idEdible = true;
    public int Energy = 38;
    public int Age = 0;
    public String Season = "Fall";
    public boolean CanBecomeGiantPlant = false;

    public Cranberries(String plantType, int XPlanting, int YPlanting) {
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
