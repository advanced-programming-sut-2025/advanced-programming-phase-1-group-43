package Model;

import java.util.Random;

public class Foraging {
    public int x;
    public int y;
    public String type;

    public boolean isGrow(){
        Random rand = new Random();
        int randNum = rand.nextInt(100);
        if(randNum == 1){
            return true;
        }
        return false;
    }

    public Foraging(int x, int y, String type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }
}
