package Controller;

import Model.NPC.Abigal.Abigal;
import Model.NPC.Harvey.Harvey;
import Model.NPC.Leah.Leah;
import Model.NPC.Robin.Robin;
import Model.NPC.Sebastian.Sebastian;

public class QuestFinish {
    public void Questlist() {
        if(Abigal.FriendShipCount >5){
            System.out.println("Abigal done!");
        }
        if(Harvey.FriendShipCount >5){
            System.out.println("Harvey done!");
        }
        if(Leah.FriendShipCount >5){
            System.out.println("Leah done!");
        }
        if(Robin.FriendShipCount >5){
            System.out.println("Robin done!");
        }
        if (Sebastian.FriendShipCount > 5) {
            System.out.println("Sebastian done!");
        }
    }
}
