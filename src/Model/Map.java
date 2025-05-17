package Model;

import java.util.List;
import java.util.Random;

public class Map {
    private Tile tiles;
    private List<Building> buildings;
    private List<NPC> npcs;
    private List<Animal> animals;
    private List<Plant> plants;

    public Tile getTile() {
        Random random = new Random();
        int x = random.nextInt() % 100;
        int y = random.nextInt() % 100;
        return new Tile(x,y);
    }
}
