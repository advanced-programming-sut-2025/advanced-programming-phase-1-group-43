package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Farm {
    private final List<Tile> cropTiles = new ArrayList<>();

    /**
     * Automatically water every crop tile on the farm.
     */
    public void autoIrrigate() {
        System.out.println("Auto-irrigating all crops...");
        for (Tile t : cropTiles) {
            t.water();
        }
    }

    /**
     * Randomly strike three tiles with lightning, destroying their crops.
     */
    public void strikeLightning() {
        System.out.println("Lightning storm! Striking random tiles...");
        if (cropTiles.isEmpty()) return;
        for (int i = 0; i < 3; i++) {
            int idx = (int)(Math.random() * cropTiles.size());
            Tile t = cropTiles.get(idx);
            t.destroyCrop();
        }
    }

    /**
     * Add a tile to be managed as a crop tile.
     */
    public void addCropTile(Tile tile) {
        cropTiles.add(tile);
    }

    /**
     * @return an unmodifiable view of crop tiles
     */
    public List<Tile> getCropTiles() {
        return Collections.unmodifiableList(new ArrayList<>(cropTiles));
    }

}
