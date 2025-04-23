package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameState implements Serializable {
    private static final long serialVersionUID = 1L;

    private final List<String> players;  // usernames
    private int currentPlayerIndex = 0;
    private int mapNumber;
    private int turnNumber = 0;
    // ... other game data: inventory, map tiles, date/time, weather, etc.

    public GameState(List<String> players) {
        if (players == null || players.isEmpty() || players.size()>3)
            throw new IllegalArgumentException("Must have 1–3 players");
        this.players = new ArrayList<>(players);
    }

    public List<String> getPlayers() {
        return Collections.unmodifiableList(new ArrayList<>(players));
    }

    public int getMapNumber() {
        return mapNumber;
    }

    public void setMapNumber(int mapNumber) {
        this.mapNumber = mapNumber;
    }

    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }

    public String getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }

    public int getTurnNumber() {
        return turnNumber;
    }

    public void advanceTurn() {
        turnNumber++;
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
    }
}
