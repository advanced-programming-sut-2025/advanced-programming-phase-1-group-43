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
            if (input.equals("help")) printHelp();
            else if (input.startsWith("game new ")) game.createNew(input);
            else if (input.startsWith("game map ")) game.selectMap(input);
            else if ("load game".equals(input)) game.load();
            else if (input.equals("exit game")) game.exitGame();
            else if (input.startsWith("next turn")) game.nextTurn();
            else if (input.startsWith("time") || input.startsWith("date") ||
                     input.startsWith("datetime") || input.startsWith("day of the week") ||
                     input.startsWith("cheat advance time") || input.startsWith("cheat advance date")) {
                timeCtrl.handle(input);
            } else if (input.startsWith("weather")) {
                weatherCtrl.handle(input);
            } else {
                System.out.println("Invalid command in game menu_write \"help\" to see valid commands.");
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    private void printHelp() {
        System.out.println("Game Menu commands:");
        System.out.println("  game new -u <u1> [u2] [u3]      Create a new game with 1–3 players");
        System.out.println("  game map <map_number>           Select a map before starting play");
        System.out.println("  load game                       Load your last saved game");
        System.out.println("  exit game                       Exit & save current session");
        System.out.println("  terminate game                  Vote to force-terminate the game");
        System.out.println("  next turn                       Advance to next player's turn");
        System.out.println("  time / date / datetime / day…   Show game time/date");
        System.out.println("  cheat advance time <X>h         Cheat: advance time");
        System.out.println("  cheat advance date <X>d         Cheat: advance date");
        System.out.println("  weather / weather forecast      Show weather");
        System.out.println("  cheat weather set <TYPE>        Cheat: set weather");
    }
}
