package Model;

import java.io.Serializable;

/**
 * Represents a single tile on the farm or in the greenhouse.
 * Stores its (x,y) position, whether it has a planted crop, and how much it has been watered.
 */
public class Tile implements Serializable {
    private static final long serialVersionUID = 1L;

    private final int x;
    private final int y;
    private boolean hasCrop;
    private int waterLevel;

    /**
     * Creates a tile at the given coordinates, with no crop planted and zero water level.
     *
     * @param x the x-coordinate of this tile
     * @param y the y-coordinate of this tile
     */
    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
        this.hasCrop = false;
        this.waterLevel = 0;
    }

    /**
     * Default constructor: places tile at (0,0).
     * You can remove this if you always want explicit coordinates.
     */
    public Tile() {
        this(0, 0);
    }

    /**
     * Plant a crop on this tile.
     * Resets water level to zero.
     */
    public void plantCrop() {
        if (!hasCrop) {
            hasCrop = true;
            waterLevel = 0;
            System.out.println(String.format("Planted crop at (%d,%d).", x, y));
        }
    }

    /**
     * Water the crop on this tile, if present.
     * Increments waterLevel by 1.
     */
    public void water() {
        if (hasCrop) {
            waterLevel++;
            System.out.println(String.format("Tile (%d,%d) watered; level now %d.", x, y, waterLevel));
        }
    }

    /**
     * Destroy the crop on this tile (e.g. by lightning).
     * Resets water level.
     */
    public void destroyCrop() {
        if (hasCrop) {
            hasCrop = false;
            waterLevel = 0;
            System.out.println(String.format("A crop was destroyed by lightning at (%d,%d).", x, y));
        }
    }

    // —— Getters —— //

    /**
     * @return the x-coordinate of this tile
     */
    public int getX() {
        return x;
    }

    /**
     * @return the y-coordinate of this tile
     */
    public int getY() {
        return y;
    }

    /**
     * @return true if a crop is planted here
     */
    public boolean hasCrop() {
        return hasCrop;
    }

    /**
     * @return how many times this tile has been watered since planting
     */
    public int getWaterLevel() {
        return waterLevel;
    }

    @Override
    public String toString() {
        return String.format("Tile(%d,%d) [crop=%s, water=%d]",
                x, y, hasCrop ? "yes" : "no", waterLevel);
    }
}
