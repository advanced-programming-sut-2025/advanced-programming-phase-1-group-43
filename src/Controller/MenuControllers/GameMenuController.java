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
            // Map-selection phase
            if (game.isAwaitingMapSelection()) {
                if (input.startsWith("game map ")) {
                    game.selectMap(input);
                    if (game.isAllMapsChosen()) {
                        System.out.println("All maps chosen — game starts now!");
                    } else {
                        System.out.println("Next player, select your map:");
                    }
                } else if (input.equals("help")) {
                    printHelp();
                } else {
                    System.out.println("In map-selection phase only 'game map <n>' is allowed. Type 'help' for commands.");
                }
                return;
            }

            // Main game commands
            if (input.equals("help")) {
                printHelp();
            }
            else if (input.startsWith("game new ")) {
                game.createNew(input);
            }
            else if (input.equals("load game")) {
                game.load();
            }
            else if (input.equals("exit game")) {
                game.exitGame();
            }
            else if (input.equals("terminate game")) {
                game.terminateGame();
            }
            else if (input.equals("terminate vote yes")) {
                game.voteTerminate(true);
            }
            else if (input.equals("terminate vote no")) {
                game.voteTerminate(false);
            }
            else if (input.equals("next turn")) {
                game.nextTurn();
            }
            else if (input.startsWith("time") || input.startsWith("date") ||
                    input.startsWith("datetime") || input.startsWith("day of the week") ||
                    input.startsWith("cheat advance time") || input.startsWith("cheat advance date")) {
                timeCtrl.handle(input);
            }
            else if (input.startsWith("weather")) {
                weatherCtrl.handle(input);
            }
            else {
                System.out.println("Invalid command in game menu—type 'help' to see valid commands.");
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    private void printHelp() {
        System.out.println("Game-Menu Commands:");
        System.out.println("  game new -u <u1> [u2] [u3]      ⇨ create new game (then select maps)");
        System.out.println("  game map <n>                    ⇨ choose map #n (turn-based, each player)");
        System.out.println("  load game                       ⇨ load saved game");
        System.out.println("  exit game                       ⇨ save & exit to main menu (creator only, on your turn)");
        System.out.println("  terminate game                  ⇨ creator starts force-terminate vote");
        System.out.println("  terminate vote yes|no           ⇨ cast your vote in termination");
        System.out.println("  next turn                       ⇨ end your turn, go to next player");
        System.out.println("  time / date / datetime / day…   ⇨ show game time/date");
        System.out.println("  cheat advance time <X>h         ⇨ cheat: advance time");
        System.out.println("  cheat advance date <X>d         ⇨ cheat: advance date");
        System.out.println("  weather / weather forecast      ⇨ show weather");
        System.out.println("  cheat weather set <TYPE>        ⇨ cheat: set weather");
    }

}
