package Controller;

import Model.NPC.Abigal.Abigal;
import Model.NPC.Harvey.Harvey;
import Model.NPC.Leah.Leah;
import Model.NPC.Robin.Robin;
import Model.NPC.Sebastian.Sebastian;

public class FriendShip {
    public void FriendShip() {
        System.out.println("Abigal :"+ Abigal.FriendShipCount);
        System.out.println("Harvey :"+ Harvey.FriendShipCount);
        System.out.println("Leah :" + Leah.FriendShipCount);
        System.out.println("Robin :" + Robin.FriendShipCount);
        System.out.println("Sebastian :" + Sebastian.FriendShipCount);
    }
}
