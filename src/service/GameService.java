package service;

import Model.GameState;
import repository.GameRepository;
import repository.UserRepository;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static util.SessionManager.gson;

public class GameService {
    private final GameRepository gameRepo;
    private final UserRepository userRepo;
    private final TimeService timeService;
    private boolean awaitingMap = false;
    private int mapsChosenCount = 0;
    /** assume we have 3 maps available */
    private static final int AVAILABLE_MAPS = 3;


    public GameService(GameRepository gameRepo,
                       UserRepository userRepo,
                       TimeService timeService) {
        this.gameRepo = gameRepo;
        this.userRepo = userRepo;
        this.timeService = timeService;
    }
    /**
     * Parses “game new -u <u1> [<u2> [<u3>]]” and creates a new GameState.
     */
    public void createNew(String command) throws Exception {
        if (gameRepo.hasLoadedGame()) {
            throw new Exception("You are already in an active game.");
        }

        String[] tokens = command.split("\\s+");
        // باید حداقل: ["game","new","-u","player1"]
        if (tokens.length < 4 || !"-u".equals(tokens[2])) {
            throw new Exception("Usage: game new -u <username1> [<username2> [<username3>]]");
        }
        // جمع‌آوری لیست بازیکن‌ها
        // همیشه سازنده را یکی اول لیست می‌گذاریم
        String creator = userRepo.getCurrent().getUsername();
        List<String> players = new ArrayList<>();
        players.add(creator);
        // سپس سایرِ نام‌های کاربری را اضافه می‌کنیم، مگر اینکه برابرِ creator باشند
        for (int i = 3; i < tokens.length; i++) {
            String u = tokens[i];
            if (u.equals(creator)) {
                // اگر اتفاقاً سازنده را دوبار وارد کرده باشد، رد کنیم
                continue;
            }
            players.add(u);
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
            if (gameRepo.isUserInAnySavedGame(u)) {
                throw new Exception("User already in another game: " + u);
            }
        }
        // ساخت وضعیت بازی
        GameState state = new GameState(players);
        // قرار دادن به‌عنوان بازی جاری و ذخیره
        gameRepo.save(state);
        awaitingMap = true;
        mapsChosenCount = 0;
        System.out.println("Game created with players: " + players);
        System.out.println("Now select map with: game map <map_number>");
    }

    /**
     * Parses “game map <mapNumber>” and assigns the chosen map.
     */
    public void selectMap(String command) throws Exception {
        if (!awaitingMap) throw new Exception("You must create a game first.");
        String[] tokens = command.split("\\s+");
        int m = Integer.parseInt(tokens[2]);
        if (m<1 || m>AVAILABLE_MAPS) throw new Exception("Invalid map number");
        GameState state = gameRepo.getCurrent();
        // هر بازیکن نقشهٔ خود را در آرایه ای نگهداری می‌کند:
        state.assignMapForCurrentPlayer(m);
        mapsChosenCount++;
        gameRepo.saveCurrent();

        if (mapsChosenCount >= state.getPlayers().size()) {
            // همه انتخاب کردند → فاز نقشه تمام
            awaitingMap = false;
            state.setCurrentPlayerIndex(0);
            state.resetTurnNumber();        // add this helper
            gameRepo.saveCurrent();
            System.out.println("All maps chosen — game starts now!");
        }
    }

    public boolean isAwaitingMapSelection() {
        return awaitingMap;
    }
    public boolean isAllMapsChosen() {
        return !awaitingMap;
    }


    /** Loads the last saved game for the current user into context. */
    public void load() throws Exception {
        String me = userRepo.getCurrent().getUsername();

        // scan every save file for one that contains me
        for (String username : userRepo.getAllUsernames()) {
            Path path = Paths.get("saves-json", username + ".json");
            if (!Files.exists(path)) continue;

            try (Reader reader = Files.newBufferedReader(path)) {
                GameState loaded = gson.fromJson(reader, GameState.class);
                if (loaded.getPlayers().contains(me)) {
                    // transfer “ownership” to the loader:
                    loaded.setCurrentOwner(me);
                    // reset turn so loader goes next:
                    int idx = loaded.getPlayers().indexOf(me);
                    loaded.setCurrentPlayerIndex(idx);
                    // install into memory AND re-save under <me>.json
                    gameRepo.setCurrent(loaded);

                    System.out.println("Loaded game for players: " + loaded.getPlayers());
                    System.out.println(
                            "Current turn: " + loaded.getTurnNumber() +
                                    ", current player: " + loaded.getCurrentPlayer());
                    return;
                }
            }
        }

        throw new Exception("No saved game found for you.");
    }


    public void nextTurn() throws Exception {
        GameState state = gameRepo.getCurrent();
        state.advanceTurn();
        // if we wrapped from last player back to first, advance the clock one hour
        if (state.getCurrentPlayerIndex() == 0) {
            timeService.advanceHours(1);
        }
        gameRepo.saveCurrent();
        System.out.println("Now it is " + state.getCurrentPlayer() + "’s turn (turn #" + state.getTurnNumber() + ").");
    }

    /** Exits the current game session for this user. */
    public void exitGame() throws Exception {
        GameState s = gameRepo.getCurrent();

        String me = userRepo.getCurrent().getUsername();
        String owner = s.getCurrentOwner();

        // only builder on their turn:
        if (!me.equals(owner)) {
            throw new Exception("Only the game creator may exit the game.");
        }
        if (!me.equals(s.getCurrentPlayer())) {
            throw new Exception("You may only exit when it is your turn.");
        }

        // save and clear
        gameRepo.saveCurrent();
        System.out.println("Game saved. Exiting to main menu.");
        gameRepo.clearCurrent();
    }

    private boolean votingInProgress = false;
    private int yesVotes = 0;
    private int votesCast = 0;

    public void terminateGame() throws Exception {
        GameState s = gameRepo.getCurrent();

        String me = userRepo.getCurrent().getUsername();
        String owner = s.getCurrentOwner();
        if (!me.equals(owner)) {
            throw new Exception("Only the game creator may force-terminate.");
        }

        if (votingInProgress) {
            throw new Exception("A vote is already in progress.");
        }

        votingInProgress = true;
        yesVotes = 1;
        votesCast = 1;
        System.out.println("Force-terminate vote started by " + me + ". (yesVotes=1)");

        // the builder’s vote counts; others will vote in their turns
    }

    public void voteTerminate(boolean yes) throws Exception {
        if (!votingInProgress) throw new Exception("No termination vote in progress.");
        GameState s = gameRepo.getCurrent();
        String me = userRepo.getCurrent().getUsername();

        // each player votes once per round
        votesCast++;
        if (yes) yesVotes++;

        // once all have voted:
        if (votesCast >= s.getPlayers().size()) {
            votingInProgress = false;
            if (yesVotes == s.getPlayers().size()) {
                // unanimous → delete
                gameRepo.deleteSave();
                gameRepo.clearCurrent();
                System.out.println("All players agreed. Game terminated permanently.");
            }
            else {
                System.out.println("Vote failed. Game continues.");
            }
        } else {
            System.out.println("Vote recorded (" + yesVotes + "/" + s.getPlayers().size() + " yes).");
        }
    }
}