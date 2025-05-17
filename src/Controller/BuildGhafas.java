package Controller;

import Model.Barn;
import Model.Coop;
import Model.Farm;

public class BuildGhafas {
    public void BuildGhafas(String BuildingName, int x, int y) {
        for(int i=0;i< Farm.xLocations.size();i++){
            for (int j = 0; j < Farm.yLocations.size(); j++) {
                if (Farm.xLocations.get(i) == x && Farm.yLocations.get(j) == y) {
                    System.out.println("You cant build here!!");
                    return;
                }
            }
        }
        Farm.xLocations.add(x);
        Farm.yLocations.add(y);
        if(BuildingName.equals("Barn")){
            Farm.Barns.add(new Barn(x,y));
        }else {
            Farm.Coops.add(new Coop(x,y));
        }
        System.out.println("Build Successful!");
        return;
    }
}
