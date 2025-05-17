package Model.NPC.Leah;

import Model.User;

public class Leah {

    public String[] Favorite = {"Salad" , "Wine","Grape"};
    public boolean Friend = false;
    public static int FriendShipCount = 0;
    public static int Xloc;
    public static int Yloc;

    void Friend() {
        User.HardWood -=12;
        User.SalamonNum -=1;
        User.Wood --;
        Friend = true;

        //Reward
        User.Money +=500;
        User.MatarSak +=3;
        User.SaladCount +=5;
    }
}
