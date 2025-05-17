package Controller;

import Model.Farm;
import Model.User;

import java.util.ArrayList;
import java.util.Arrays;

public class WalkingController {
    // baraye emtyazi
    int NextMove = -1;// 1 --> Right or Left          0 ---> Up or Down
    public ArrayList<Object> CanWalk(int x, int y, int SecondX, int SecondY) {
        int Energyy = 0;
        int timeOfTurn = 0;
        boolean canEalk = true;
        int TotalEnergy = 0;
        boolean Ghash = false;
        while (x != SecondX || y != SecondY || canEalk) {
            int isWalk = 0;

            if (x > SecondX) {
                if (!Farm.xLocations.contains(x - 1) && !Farm.yLocations.contains(y)) {
                    x--;
                    Energyy += 1;
                    isWalk = 1;
                    if(NextMove == 0){
                        timeOfTurn ++;
                    }
                    NextMove = 1;
                }
            } else if (x < SecondX) {
                if (!Farm.xLocations.contains(x + 1) && !Farm.yLocations.contains(y)) {
                    x++;
                    Energyy += 1;
                    isWalk = 1;
                    if(NextMove == 0){
                        timeOfTurn ++;
                    }
                    NextMove = 1;
                }
            }

            if (y > SecondY) {
                if (!Farm.yLocations.contains(y - 1) && !Farm.xLocations.contains(x)) {
                    y--;
                    Energyy += 1;
                    isWalk = 1;
                    if(NextMove == 1){
                        timeOfTurn ++;
                    }
                    NextMove = 0;
                }
            } else if (y < SecondY) {
                if (!Farm.yLocations.contains(y + 1) && !Farm.xLocations.contains(x)) {
                    y++;
                    Energyy += 1;
                    isWalk = 1;
                    if(NextMove == 1){
                        timeOfTurn ++;
                    }
                    NextMove = 0;
                }
            }
            if (isWalk == 0 && x != SecondX && y != SecondY) {
                canEalk = false;
            }
        }
        TotalEnergy = (timeOfTurn + (Energyy * 10))/20;
        return new ArrayList<>(Arrays.asList(canEalk, TotalEnergy));
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
