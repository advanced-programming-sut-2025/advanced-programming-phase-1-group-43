package Model;

import java.util.ArrayList;
import java.util.List;

public class Greenhouse {
    private int firstX;
    private int firstY;
    private int secondX;
    private int secondY;

    private boolean built;
    private List<Tile> cropTiles;

    /** کانستراکتور پیش‌فرض (غیرساخته) */
    public Greenhouse() {
        this.built = false;
    }

    /**
     * کانستراکتور با مختصات (برای ساخت مستقیم یا تست)
     */
    public Greenhouse(int firstX, int firstY, int secondX, int secondY) {
        this.firstX = firstX;
        this.firstY = firstY;
        this.secondX = secondX;
        this.secondY = secondY;
        this.built = false;
        this.cropTiles = new ArrayList<>();
    }

    /**
     * ساخت گلخانه با مصرف منابع
     */
    public void build(int wood, int stone) {
        if (wood < 500 || stone < 1000)
            throw new IllegalArgumentException("Not enough resources to build greenhouse");

        this.built = true;
        // initialize cropTiles as a 5×6 grid between (firstX,firstY) and (secondX,secondY)
        cropTiles = new ArrayList<>();
        for (int x = firstX; x < firstX + 5; x++) {
            for (int y = firstY; y < firstY + 6; y++) {
                cropTiles.add(new Tile(x, y));
            }
        }
    }

    public boolean isBuilt() {
        return built;
    }

    /**
     * آبیاری تمام خانه‌های داخل گلخانه
     */
    public void irrigateAll() {
        if (!built)
            throw new IllegalStateException("Greenhouse not built yet");
        for (Tile t : cropTiles) {
            t.water();
        }
    }
}
