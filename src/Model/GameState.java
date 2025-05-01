package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameState implements Serializable {
    private static final long serialVersionUID = 1L;

    private final List<String> players;  // usernames
    private List<Integer> playerMaps;
    private int currentPlayerIndex;
    private int mapNumber;
    private int turnNumber = 0;
    private String currentOwner;
    // ... other game data: inventory, map tiles, date/time, weather, etc.

    public GameState(List<String> players) {
        if (players == null || players.isEmpty() || players.size()>3)
            throw new IllegalArgumentException("Must have 1–3 players");
        this.players = new ArrayList<>(players);
        this.playerMaps = new ArrayList<>(Collections.nCopies(players.size(), 0));
        this.currentOwner = players.get(0);
    }

    public void setCurrentPlayerIndex(int idx) {
        if (idx < 0 || idx >= players.size())
            throw new IllegalArgumentException("Bad player index");
        this.currentPlayerIndex = idx;
    }

    public String getCurrentOwner() {
        return currentOwner;
    }

    public void setCurrentOwner(String currentOwner) {
        this.currentOwner = currentOwner;
    }

    public void assignMapForCurrentPlayer(int mapNumber) {
        playerMaps.set(currentPlayerIndex, mapNumber);
        // پس از انتخاب، نوبت به بعدی:
        advanceTurn();
    }

    public List<Integer> getPlayerMaps() {
        return Collections.unmodifiableList(playerMaps);
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

    public void resetTurnNumber(){
        this.turnNumber = 0;
    }
}
