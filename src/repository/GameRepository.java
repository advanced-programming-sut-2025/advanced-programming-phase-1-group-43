package repository;

import Model.GameState;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GameRepository {
    private final Path storageDir = Paths.get("saves-json");
    private final UserRepository userRepo;
    private GameState currentState;
    private final Gson gson = new Gson();
    private final Type gameType = new TypeToken<GameState>(){}.getType();

    public GameRepository(UserRepository userRepo) throws IOException {
        this.userRepo = userRepo;
        if (!Files.exists(storageDir)) {
            Files.createDirectories(storageDir);
        }
    }

    /**
     * Loads the last saved game for the current user as JSON.
     */
    public GameState loadLast() throws IOException {
        String me = userRepo.getCurrent().getUsername();
        // first, try file named after user
        Path file = storageDir.resolve(me + ".json");
        if (Files.exists(file)) {
            return readState(file);
        }
        // else scan all saves
        try (DirectoryStream<Path> ds = Files.newDirectoryStream(storageDir, "*.json")) {
            for (Path p : ds) {
                GameState st = readState(p);
                if (st.getPlayers().contains(me)) {
                    currentState = st;
                    return st;
                }
            }
        }
        throw new IOException("No saved game found for user: " + me);
    }

    private GameState readState(Path file) throws IOException {
        try (Reader reader = Files.newBufferedReader(file)) {
            GameState state = gson.fromJson(reader, gameType);
            if (state == null) throw new IOException("Corrupted game data in " + file.getFileName());
            currentState = state;
            return state;
        }
    }

    /**
     * Returns the GameState most recently loaded by loadLast().
     * Throws if no game has been loaded yet.
     */
    public GameState getCurrent() {
        if (currentState == null) {
            throw new IllegalStateException("No game loaded. Call loadLast() first.");
        }
        return currentState;
    }

    /**
     * Saves (overwrites) the current state for the user who created it.
     */
    public void saveCurrent() throws IOException {
        if (currentState == null) {
            throw new IllegalStateException("No game to save.");
        }
        String owner = currentState.getCurrentOwner();
        Path file = storageDir.resolve(owner + ".json");
        try (Writer writer = Files.newBufferedWriter(file)) {
            gson.toJson(currentState, writer);
        }
    }

    public void save(GameState state) throws IOException {
        this.currentState = state;
        saveCurrent();   // متد existing که وضعیت currentState را به فایل می‌نویسد
    }

    // متد کمکی برای تعیین state جاری بدون ذخیره
    public void setCurrent(GameState state) {
        this.currentState = state;
    }

    /** Remove in-memory reference so that getCurrent() fails until next load/create */
    public void clearCurrent() {
        this.currentState = null;
    }

    /** Delete the saved JSON on disk for the current owner */
    public void deleteSave() throws IOException {
        if (currentState==null) return;
        // delete every file whose persisted state contains this same players‐list
        try (DirectoryStream<Path> ds = Files.newDirectoryStream(storageDir,"*.json")) {
            for (Path p: ds) {
                GameState s = readState(p);
                // if this file holds the same players as our in-memory game
                if (s.getPlayers().equals(currentState.getPlayers())) {
                    Files.deleteIfExists(p);
                }
            }
        }
    }

    public boolean hasSavedGameFor(String username) {
        Path file = storageDir.resolve(username + ".json");
        return Files.exists(file);
    }

    /** Return true if an in-memory game is already loaded/active */
    public boolean hasLoadedGame() {
        return currentState != null;
    }

    public boolean isUserInAnySavedGame(String username) throws IOException {
        if (!Files.exists(storageDir)) {
            return false;
        }
        // Java 8: use DirectoryStream to iterate files
        try (DirectoryStream<Path> ds = Files.newDirectoryStream(storageDir, "*.json")) {
            for (Path file : ds) {
                try (Reader reader = Files.newBufferedReader(file)) {
                    GameState state = gson.fromJson(reader, gameType);
                    if (state != null && state.getPlayers().contains(username)) {
                        return true;
                    }
                } catch (Exception ignore) {
                    // skip malformed files
                }
            }
        }
        return false;
    }
}

