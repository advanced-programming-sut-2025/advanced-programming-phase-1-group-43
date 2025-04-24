package Model;

import java.util.List;

public class Greenhouse {
    private boolean built;
    private List<Tile> cropTiles;

    public Greenhouse() {
        this.built = false;
    }

    public void build(int wood, int stone) {
        if (wood < 500 || stone < 1000) throw new IllegalArgumentException("Not enough resources");
        built = true;
        // initialize cropTiles with 5x6 interior
    }

    public boolean isBuilt() {
        return built;
    }

    public void irrigateAll() {
        if (!built) throw new IllegalStateException("Greenhouse not built");
        for (Tile t : cropTiles) t.water();
    }
}
