package Controller;

import Model.App;
import Model.Farm;
import Model.plant;

public class FertilizeController {
    public String Fertilize(String FertilizeType, int XPlanting, int YPlanting) {
        for(int i=0;i< Farm.xLocations.size();i++){
            if(Farm.xLocations.get(i) == XPlanting){
                if(Farm.yLocations.get(i) == YPlanting){
                    for(plant Plant :App.AllPlantInFarm){
                        if(Plant.XPlanting == XPlanting && Plant.YPlanting == YPlanting){
                            Plant.Fertilize(FertilizeType);
                        }
                    }
                }
            }
        }
        return "Not Plant found";
    }
}
