package View;

import Controller.*;
import Model.enums.Commmands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NPCview {
    public void NPCview(String Message) {
        boolean MeetNpc = Pattern.matches(Message, Commmands.MeetNpc.getPattern());
        boolean gift = Pattern.matches(Message, Commmands.GiftNpc.getPattern());
        boolean FriendShip = Pattern.matches(Message, Commmands.FriendShip.getPattern());
        boolean QuestList = Pattern.matches(Message, Commmands.Quest.getPattern());
        boolean QuestFinish = Pattern.matches(Message, Commmands.QuestFinish.getPattern());

        if (MeetNpc) {
            Pattern pattern = Pattern.compile(Commmands.MeetNpc.getPattern());
            Matcher matcher = pattern.matcher(Message);
            matcher.find();

            String name = matcher.group("Name");
            Controller.MeetNpc meetNpc = new Controller.MeetNpc();
            meetNpc.MeetNpc(name);
        }else if (gift) {
            Pattern pattern = Pattern.compile(Commmands.GiftNpc.getPattern());
            Matcher matcher = pattern.matcher(Message);
            matcher.find();

            String name = matcher.group("Name");
            String item = matcher.group("Item");
            GiftNpc giftNpc = new GiftNpc();
            giftNpc.GiftNpc(name, item);
        }else if (FriendShip) {
            Controller.FriendShip friendShip = new Controller.FriendShip();
            friendShip.FriendShip();
        }else if (QuestList) {
            Questlist questlist = new Questlist();
            questlist.Questlist();
        }else if (QuestFinish) {
            Controller.QuestFinish questFinish = new Controller.QuestFinish();
            questFinish.Questlist();
        }
    }
}
