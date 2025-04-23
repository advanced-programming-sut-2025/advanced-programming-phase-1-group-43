package Controller.MenuControllers;


import service.GameService;

public class GameMenuController {
    private final GameService game;
    public GameMenuController(GameService game) { this.game = game; }

    public void handle(String input) {
        try {
            if (input.startsWith("game new ")) game.createNew(input);
            else if (input.startsWith("game map ")) game.selectMap(input);
            else if ("load game".equals(input)) game.load();
            else if (input.startsWith("next turn")) game.nextTurn();
            else System.out.println("Invalid command in game menu");
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
}
