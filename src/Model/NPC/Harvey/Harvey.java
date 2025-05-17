package Model.NPC.Harvey;

import Model.User;

public class Harvey {
    public String[] Favorite = {"Pickle" , "Wine","Coffee"};
    public boolean Friend = false;
    public static int FriendShipCount = 0;
    public static int Xloc;
    public static int Yloc;


    void Friend() {
        User.Plantnum -=12;
        User.SalamonNum -=1;
        User.WineNum --;
        Friend = true;

        //Reward
        User.Money +=750;
        FriendShipCount++;
        User.SaladCount +=5;
    }


}
