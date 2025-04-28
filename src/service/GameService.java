package service;

import Model.GameState;
import repository.GameRepository;
import repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class GameService {
    private final GameRepository gameRepo;
    private final UserRepository userRepo;
    /** assume we have 3 maps available */
    private static final int AVAILABLE_MAPS = 3;


    public GameService(GameRepository gameRepo, UserRepository userRepo) {
        this.gameRepo = gameRepo;
        this.userRepo = userRepo;
    }
    /**
     * Parses “game new -u <u1> [<u2> [<u3>]]” and creates a new GameState.
     */
    public void createNew(String command) throws Exception {
        String[] tokens = command.split("\\s+");
        // باید حداقل: ["game","new","-u","player1"]
        if (tokens.length < 4 || !"-u".equals(tokens[2])) {
            throw new Exception("Usage: game new -u <username1> [<username2> [<username3>]]");
        }
        // جمع‌آوری لیست بازیکن‌ها
        // always include the creator first:
        String creator = userRepo.getCurrent().getUsername();
        List<String> players = new ArrayList<>();
        players.add(creator);
        // then add the “other” usernames they typed
        for (int i = 3; i < tokens.length; i++) {
            players.add(tokens[i]);
        }
        // اعتبارسنجی تعداد
        if (players.size() < 1 || players.size() > 3) {
            throw new Exception("Must specify between 1 and 3 players");
        }
        // اعتبارسنجی وجود هر کاربر
        for (String u : players) {
            if (userRepo.findByUsername(u) == null) {
                throw new Exception("No such user: " + u);
            }
        }
        // ساخت وضعیت بازی
        GameState state = new GameState(players);
        // قرار دادن به‌عنوان بازی جاری و ذخیره
        gameRepo.save(state);
        System.out.println("Game created with players: " + players);
        System.out.println("Now select map with: game map <map_number>");
    }

    /**
     * Parses “game map <mapNumber>” and assigns the chosen map.
     */
    public void selectMap(String command) throws Exception {
        String[] tokens = command.split("\\s+");
        if (tokens.length != 3) {
            throw new Exception("Usage: game map <map_number>");
        }
        int m;
        try {
            m = Integer.parseInt(tokens[2]);
        } catch (NumberFormatException e) {
            throw new Exception("Map number must be an integer");
        }
        if (m < 1 || m > AVAILABLE_MAPS) {
            throw new Exception("Invalid map number, must be 1–" + AVAILABLE_MAPS);
        }
        GameState state = gameRepo.getCurrent();
        state.setMapNumber(m);
        gameRepo.saveCurrent();
        System.out.println("Map #" + m + " selected. You can now start playing.");
    }


    /** Loads the last saved game for the current user into context. */
    public void load() throws Exception {
        GameState state = gameRepo.loadLast();
        System.out.println("Loaded game for players: " + state.getPlayers());
        System.out.println("Current map: " + state.getMapNumber() +
                ", turn: " + state.getTurnNumber() +
                ", current player: " + state.getCurrentPlayer());
    }

    public void nextTurn() throws Exception {
        GameState state = gameRepo.getCurrent();
        state.advanceTurn();
        gameRepo.saveCurrent();
        System.out.println("Now it is " + state.getCurrentPlayer() + "’s turn (turn #" + state.getTurnNumber() + ").");
    }

    /** Exits the current game session for this user. */
    public void exitGame() throws Exception {
        gameRepo.saveCurrent();
        System.out.println("Game saved. Exiting to main menu.");
        gameRepo.clearCurrent();    // you'll need a method to clear in-memory state
    }

    public void terminateGame() throws Exception {
        gameRepo.deleteSave();      // implement this to delete the JSON file
        System.out.println("Game has been terminated and cannot be reloaded.");
        gameRepo.clearCurrent();
    }
}