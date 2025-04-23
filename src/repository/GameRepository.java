package repository;

import Model.GameState;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
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
        String username = userRepo.getCurrent().getUsername();
        Path file = storageDir.resolve(username + ".json");
        if (!Files.exists(file)) {
            throw new IOException("No saved game found for user: " + username);
        }
        try (Reader reader = Files.newBufferedReader(file)) {
            GameState state = gson.fromJson(reader, gameType);
            if (state == null) throw new IOException("Corrupted game data");
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
        String owner = currentState.getPlayers().get(0);
        Path file = storageDir.resolve(owner + ".json");
        try (Writer writer = Files.newBufferedWriter(file)) {
            gson.toJson(currentState, writer);
        }
    }
}
