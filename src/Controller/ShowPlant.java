package Controller;

import Model.Farm;
import Model.Plant;
import Model.PlantAndCrops.Beet;

public class ShowPlant {
    public void ShowPlant(int x,int y){
        int isFind = 0;
        for(Plant plant: Farm.AllPlants){
            if (plant.XPlanting ==x && plant.YPlanting ==y){
                if(plant.plantType == "Beet"){
                    isFind = 1;
                    Beet.getInfo();
                }
                //TODO
            }
        }
    }
}
