package Model.NPC.Abigal;

import Model.Stone;
import Model.User;

public class Abigal {
    public String[] Favorite = {"Stone" , "Iron Mineral","Coffee"};
    public boolean Friend = false;
    public static int FriendShipCount = 0;
    public static int Xloc;
    public static int Yloc;

    void Friend() {
        User.GoldBulion --;
        User.PumpkinNum -=1;
        User.Wheat -=50;
        Friend = true;

        //Reward
        FriendShipCount +=2;
        User.Money +=500;
    }



}
