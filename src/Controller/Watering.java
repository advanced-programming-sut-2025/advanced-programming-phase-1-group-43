package Controller;

import Model.App;
import Model.User;

public class Watering {
    public String WateringPlant(int x, int y){
        for (int i=0;i< App.AllPlantInFarm.size();i++){
            if(App.AllPlantInFarm.get(i).XPlanting == x && App.AllPlantInFarm.get(i).YPlanting == y){
                App.AllPlantInFarm.get(i).dayNotWatering = 0;
                return "Water Plant successfully";
            }
        }
        return "Not Plant found";
    }
    public int HowMuchWater(){
        return User.WaterVolume;
    }
}



