package Model;

import java.io.Serializable;

public class Tile implements Serializable {
    private boolean hasCrop;
    private int waterLevel;

    public Tile() {
        this.hasCrop = false;
        this.waterLevel = 0;
    }

    /**
     * Mark this tile as containing a crop.
     */
    public void plantCrop() {
        hasCrop = true;
        waterLevel = 0;
    }

    /**
     * Water the crop on this tile, if any.
     */
    public void water() {
        if (hasCrop) {
            waterLevel++;
            System.out.println("Tile watered, level now " + waterLevel);
        }
    }

    /**
     * Destroy the crop on this tile (e.g. by lightning).
     */
    public void destroyCrop() {
        if (hasCrop) {
            hasCrop = false;
            waterLevel = 0;
            System.out.println("A crop was destroyed by lightning on this tile.");
        }
    }

    public boolean hasCrop() { return hasCrop; }
    public int getWaterLevel() { return waterLevel; }
}


