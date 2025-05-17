package Model.NPC.Sebastian;

import Model.User;

public class Sebastian {
    public String[] Favorite = {"Feather" , "Pumpkin Pie","Pizza"};
    public boolean Friend = false;
    public static int FriendShipCount = 0;
    public static int Xloc;
    public static int Yloc;

    void Friend() {
        User.IronNum -=50;
        User.inventory -= "Pumpkin Pie";
        User.StoneNum -=50;
        Friend = true;

        //Reward
        User.DiamondNum +=2;
        User.Money +=5000;
        User.Kwartz +=50;
    }
}
