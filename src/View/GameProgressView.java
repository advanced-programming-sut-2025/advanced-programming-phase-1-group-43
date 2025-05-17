package View;

import Controller.FertilizeController;
import Model.User;
import Model.enums.Commmands;

import javax.jws.soap.SOAPBinding;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GameProgressView {
    public void GameProgress (String Message){
        boolean CraftInf = Pattern.matches(Message, Commmands.CraftInf.getPattern());
        boolean PlantInf = Pattern.matches(Message, Commmands.showPlantInf.getPattern());
        boolean Fertilize = Pattern.matches(Message, Commmands.Fertilize.getPattern());
        boolean HowMuchWater = Pattern.matches(Message, Commmands.HowMuchWater.getPattern());
        boolean Watering = Pattern.matches(Message, Commmands.Watering.getPattern());
        boolean Harvest = Pattern.matches(Message, Commmands.Harvest.getPattern());

        if(CraftInf){
            Pattern pattern = Pattern.compile(Commmands.CraftInf.getPattern());
            Matcher matcher = pattern.matcher(Message);
            matcher.find();

            String CraftType = matcher.group("CraftName");
            Controller.showTreeInf showTreeInf = new Controller.showTreeInf();
            showTreeInf.printTreeInfo(CraftType);
        }else if(PlantInf){
            Pattern pattern = Pattern.compile(Commmands.CraftInf.getPattern());
            Matcher matcher = pattern.matcher(Message);
            matcher.find();

            int x = Integer.parseInt(matcher.group("X"));
            int y = Integer.parseInt(matcher.group("Y"));
            Controller.ShowPlant showPlant = new Controller.ShowPlant();
            showPlant.ShowPlant(x,y);
        }else if(Fertilize){
            Pattern pattern = Pattern.compile(Commmands.CraftInf.getPattern());
            Matcher matcher = pattern.matcher(Message);
            matcher.find();

            String FertilizeType = matcher.group("Fertilizer");
            String Direction = matcher.group("Direction");
            FertilizeController FertilizeController = new FertilizeController();
            if(Direction.equals("up")){
                FertilizeController.Fertilize(FertilizeType, User.XLoc -1, User.YLoc);
            }else if(Direction.equals("down")){
                FertilizeController.Fertilize(FertilizeType, User.XLoc + 1, User.YLoc);
            }else if(Direction.equals("left")){
                FertilizeController.Fertilize(FertilizeType, User.XLoc , User.YLoc- 1);
            }else if(Direction.equals("right")){
                FertilizeController.Fertilize(FertilizeType, User.XLoc, User.YLoc+1);
            }
        }else if(HowMuchWater){
            System.out.println(User.Water);
        }else if(Watering){
            Pattern pattern = Pattern.compile(Commmands.Watering.getPattern());
            Matcher matcher = pattern.matcher(Message);
            matcher.find();

            int x = Integer.parseInt(matcher.group("X"));
            int y = Integer.parseInt(matcher.group("Y"));
            Controller.Watering watering = new Controller.Watering();
            watering.WateringPlant(x,y);
        }else if (Harvest){
            Pattern pattern = Pattern.compile(Commmands.Harvest.getPattern());
            Matcher matcher = pattern.matcher(Message);
            matcher.find();

            int x = Integer.parseInt(matcher.group("X"));
            int y = Integer.parseInt(matcher.group("Y"));
            Controller.Harvest harvest = new Controller.Harvest();
            harvest.Harvest(x, y);
        }
    }
}
