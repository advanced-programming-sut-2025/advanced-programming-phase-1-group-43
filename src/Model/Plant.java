package Model;

import Model.enums.PlantType;

public class Plant {
    
    private PlantType plantType;
    private int growthStage;
    private boolean watered;

    public boolean isHarvestable() {
        return false;
    }

    public void grow() {

    }
}
