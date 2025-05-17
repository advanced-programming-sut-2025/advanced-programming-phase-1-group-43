package View;

import Model.User;
import Model.enums.Commmands;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GameMapAndMoveView {
    public GameMapAndMoveView(String message) {
        boolean walk = Pattern.matches(message, Commmands.Walk.getPattern());
        boolean printMap = Pattern.matches(message, Commmands.PrintMap.getPattern());

        if (walk) {
            Pattern pattern = Pattern.compile(Commmands.Walk.getPattern());
            Matcher matcher = pattern.matcher(message);
            matcher.find();

            int x = Integer.parseInt(matcher.group("X"));
            int y = Integer.parseInt(matcher.group("Y"));
            Controller.WalkingController walkingController = new Controller.WalkingController();
            ArrayList<Object> result = walkingController.CanWalk(User.XLoc,User.YLoc,x,y);
            if(!(boolean)result.get(0)){
                System.out.println("You can't walk because the road is blocked.");
            }else {
                System.out.println("You can walk and the energy required is "+(int)result.get(1));
            }
        }else if (printMap) {
            Pattern pattern = Pattern.compile(Commmands.PrintMap.getPattern());
            Matcher matcher = pattern.matcher(message);
            matcher.find();

            int x = Integer.parseInt(matcher.group("X"));
            int y = Integer.parseInt(matcher.group("Y"));
            int size = Integer.parseInt(matcher.group("Size"));
            Controller.PrintMap pMap = new Controller.PrintMap();
            pMap.printMap(x,y,size);
        }
    }
}