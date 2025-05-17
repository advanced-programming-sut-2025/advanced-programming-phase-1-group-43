package Controller;

import Model.App;
import Model.Farm;
import Model.plant;

public class Harvest {
    public String Harvest(int x,int y){
        plant plantForRemove = null;
        int IsSuccess = 0;
        for(plant plant: App.AllPlantInFarm){
            if(plant.XPlanting == x && plant.YPlanting == y){
                if(plant.Age == 1){
                    //TODO
                    plantForRemove = plant;
                    IsSuccess = 1;
                }
            }
        }
        if(IsSuccess == 1){
            App.AllPlantInFarm.remove(plantForRemove);
            Farm.xLocations.remove(plantForRemove.XPlanting);
            Farm.yLocations.remove(plantForRemove.YPlanting);
            return "Harvest Success";
        }else{
            return "No Plant Found";
        }
    }
}
