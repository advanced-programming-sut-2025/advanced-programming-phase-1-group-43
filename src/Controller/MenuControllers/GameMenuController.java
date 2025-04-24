package Controller.MenuControllers;


import Controller.TimeController;
import Controller.WeatherController;
import service.GameService;

public class GameMenuController {
    private final GameService game;
    private final TimeController timeCtrl;
    private final WeatherController weatherCtrl;

    public GameMenuController(GameService game, TimeController timeCtrl, WeatherController weatherCtrl) {
        this.game = game;
        this.timeCtrl = timeCtrl;
        this.weatherCtrl = weatherCtrl;
    }

    public void handle(String input) {
        try {
            if (input.startsWith("game new ")) game.createNew(input);
            else if (input.startsWith("game map ")) game.selectMap(input);
            else if ("load game".equals(input)) game.load();
            else if (input.startsWith("next turn")) game.nextTurn();
            else if (input.startsWith("time") || input.startsWith("date") || input.startsWith("datetime") || input.startsWith("day of the week") || input.startsWith("cheat advance time") || input.startsWith("cheat advance date")) {
                timeCtrl.handle(input);
            } else if (input.startsWith("weather")) {
                weatherCtrl.handle(input);
            } else {
                System.out.println("Invalid command in game menu");
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
}
