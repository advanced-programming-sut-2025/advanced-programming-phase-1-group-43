package service;

import Model.GameState;
import repository.GameRepository;

public class GameService {
    private final GameRepository gameRepo;

    public GameService(GameRepository gameRepo) {
        this.gameRepo = gameRepo;
    }

    /**
     * Parses “game new -u <u1> [<u2> [<u3>]]” and creates a new GameState.
     */
    public void createNew(String command) throws Exception {
        // 1. parse player usernames (1 to 3)
        // 2. validate each exists via userRepo (throw if not)
        // 3. instantiate GameState with players, default map = null
        // 4. gameRepo.save(state)
    }

    /**
     * Parses “game map <mapNumber>” and assigns the chosen map.
     */
    public void selectMap(String command) throws Exception {
        // 1. parse integer mapNumber
        // 2. validate 1 <= mapNumber <= availableMaps
        // 3. fetch current GameState, call state.setMap(mapNumber)
        // 4. gameRepo.save(state)
    }

    /** Loads the last saved game for the current user into context. */
    public void load() throws Exception {
        GameState state = gameRepo.loadLast();
        // حالا state داخل currentState ریخته شده
    }

    public void nextTurn() throws Exception {
        GameState state = gameRepo.getCurrent();
        state.advanceTurn();
        gameRepo.saveCurrent();
    }

    /** Exits the current game session for this user. */
    public void exitGame() throws Exception {
        // Optionally prompt for save then clear current session state
    }
}