package Controller;

import Model.App;
import Model.Farm;
import Model.enums.PlantType;
import Model.Plant;

public class PlantingController {
    public String Planting(PlantType PlantType, int x, int y){
        for(int i = 0; i< Farm.xLocations.size(); i++){
            if(x == Farm.xLocations.get(i)){
                if (y == Farm.yLocations.get(i)){
                    return "You cant Planting here because here has plant ";
                }
            }
        }
        Farm.xLocations.add(x);
        Farm.yLocations.add(y);
        App.AllPlantInFarm.add(new Plant(PlantType,x,y));
        return "Planting successfully";
    }

}
