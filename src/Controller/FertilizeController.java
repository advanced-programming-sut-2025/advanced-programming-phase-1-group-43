package Controller;

import Model.App;
import Model.Farm;
import Model.Plant;

public class FertilizeController {
    public String Fertilize(String FertilizeType, int XPlanting, int YPlanting) {
        for(int i=0;i< Farm.xLocations.size();i++){
            if(Farm.xLocations.get(i) == XPlanting){
                if(Farm.yLocations.get(i) == YPlanting){
                    for(Plant plant :App.AllPlantInFarm){
                        if(plant.XPlanting == XPlanting && plant.YPlanting == YPlanting){
                            plant.Fertilize(FertilizeType);
                        }
                    }
                }
            }
        }
        return "Not Plant found";
    }
}
