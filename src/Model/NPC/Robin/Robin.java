package Model.NPC.Robin;

import Model.User;

public class Robin {

    public String[] Favorite = {"Spaghetti" , "Wood","Iron Ingots"};
    public boolean Friend = false;
    public static int FriendShipCount = 0;
    public static int Xloc;
    public static int Yloc;

    void Friend() {
        User.Wood -=1000;
        User.IronIngots -=10;
        User.Wood -= 80;
        Friend = true;

        //Reward
        User.Money +=1000;
        User.BeeHouse +=3;
        User.Money +=25000;
    }
}
