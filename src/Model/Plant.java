package Model;

import java.util.Random;

public class Plant {
    public String plantType;
    public int XPlanting;
    public int YPlanting;
    public int dayNotWatering = 0;
    public double Age;
    public Plant(String plantType, int XPlanting, int YPlanting) {
        this.plantType = plantType;
        this.XPlanting = XPlanting;
        this.YPlanting = YPlanting;
    }
    //    private int growthStage;
//    private String Source;
//    private boolean watered;
//    private int GrowTime;
//    private boolean MultiHarvest;
//    private int HarvestTime;
//    private int Price;
//    private boolean CanEat;
//    private int Energy;
//    private Season HarvestSeason;
//    private boolean Giant;

    public boolean isHarvestable() {
        return false;
    }

    public void grow() {

    }

    public void PlantInformation(){
        System.out.println("Name: ");
        System.out.println("");
        System.out.println();
        //
        //
        //
        //
        //

    }

    public boolean CrowAttackChance(){
        Random rand = new Random();
        int Randomint = rand.nextInt();
        if(Randomint%4 == 0){
            return true;
        }
        return false;
    }
    public void Fertilize(String FertilizeType){
        //TODO
    }

}
