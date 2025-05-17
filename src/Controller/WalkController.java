package Controller;

import Model.Farm;
import Model.User;

import java.util.ArrayList;
import java.util.Arrays;

public class WalkController {
    public ArrayList<Object> CanWalk(int x, int y, int SecondX, int SecondY) {
        double Energyy = 0.0;

        while (x != SecondX || y != SecondY) {
            int isWalk = 0;

            if (x > SecondX) {
                if (!Farm.xLocations.contains(x - 1) && !Farm.yLocations.contains(y)) {
                    x--;
                    Energyy += 1.0 / 20.0;
                    isWalk = 1;
                }
            } else if (x < SecondX) {
                if (!Farm.xLocations.contains(x + 1) && !Farm.yLocations.contains(y)) {
                    x++;
                    Energyy += 1.0 / 20.0;
                    isWalk = 1;
                }
            }

            if (y > SecondY) {
                if (!Farm.yLocations.contains(y - 1) && !Farm.xLocations.contains(x)) {
                    y--;
                    Energyy += 1.0 / 20.0;
                    isWalk = 1;
                }
            } else if (y < SecondY) {
                if (!Farm.yLocations.contains(y + 1) && !Farm.xLocations.contains(x)) {
                    y++;
                    Energyy += 1.0 / 20.0;
                    isWalk = 1;
                }
            }

            if (Energyy == 0.0) {
                System.out.println("GHASH KARD");
                break;
            }

            if (isWalk == 0) {
                System.out.println("Can't walk");
                return new ArrayList<>(Arrays.asList(false, 0.0));
            }
        }

        return new ArrayList<>(Arrays.asList(true, Energyy));
    }

    public boolean MainWalk(int x, int y, int SecondX, int SecondY) {
        while(x == SecondX && y == SecondY) {
            int isWalk = 0;
            if (x > SecondX) {
                if(!Farm.xLocations.contains(x-1) && !Farm.yLocations.contains(y)) {
                    x --;
                    User.Energy -= 1/20;
                    isWalk = 1;
                }
            }else if(x < SecondX){
                if(!Farm.xLocations.contains(x+1) && !Farm.yLocations.contains(y)) {
                    x ++;
                    User.Energy += 1/20;
                    isWalk = 1;
                }
            }
            if(y > SecondY) {
                if(!Farm.yLocations.contains(y-1) && !Farm.xLocations.contains(x)) {
                    y--;
                    User.Energy -= 1/20;
                    isWalk = 1;
                }
            }else if(y < SecondY){
                if(!Farm.yLocations.contains(y+1) && !Farm.xLocations.contains(x)) {
                    y++;
                    User.Energy += 1/20;
                    isWalk = 1;
                }
            }
            if(User.Energy==0){
                System.out.println("GHASH KARD");
                x = Farm.xLocations.indexOf(x);
                y = Farm.yLocations.indexOf(y);
            }
            if(isWalk == 1){
                if(x != SecondX && y != SecondY){
                    System.out.println("Can't walk");
                    return false;
                }
            }

        }
        return true;
    }
}