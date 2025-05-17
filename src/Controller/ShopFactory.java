package Controller;

import Model.Shop;
import Model.ShopItem;
import Model.enums.SeasonEnum;

import java.util.Map;
import java.util.HashMap;

public class ShopFactory {
    public static Shop createBlacksmith() {
        Map<String, ShopItem> stock = new HashMap<>();
        stock.put("Metal Bar", new ShopItem("Metal Bar", 300, new SeasonEnum[]{SeasonEnum.SPRING,SeasonEnum.SUMMER,SeasonEnum.FALL,SeasonEnum.WINTER}, 10));
        stock.put("Coal", new ShopItem("Coal", 150, SeasonEnum.values(), 20));
        return new Shop("Blacksmith", 9, 16, stock);
    }
    public static Shop createJojaMart() {
        Map<String, ShopItem> stock = new HashMap<>();
        stock.put("Wheat Seeds", new ShopItem("Wheat Seeds", 30, new SeasonEnum[]{SeasonEnum.SPRING,SeasonEnum.SUMMER}, 50));
        stock.put("Corn Seeds", new ShopItem("Corn Seeds", 150, new SeasonEnum[]{SeasonEnum.SUMMER,SeasonEnum.FALL}, 30));
        return new Shop("JojaMart", 9, 23, stock);
    }
    public static Shop createPierre() {
        Map<String, ShopItem> stock = new HashMap<>();
        stock.put("Parsnip Seeds", new ShopItem("Parsnip Seeds", 20, new SeasonEnum[]{SeasonEnum.SPRING}, 100));
        stock.put("Potato Seeds", new ShopItem("Potato Seeds", 50, new SeasonEnum[]{SeasonEnum.SPRING}, 80));
        return new Shop("Pierre", 9, 17, stock);
    }

    public static Shop createCarpenter() {
        return null;
    }

    public static Shop createFishShop() {
        return null;
    }

    public static Shop createMarnie() {
        return null;
    }

    public static Shop createSaloon() {
        return null;
    }
    // similarly createCarpenter, createFishShop, createMarnie, createSaloon...
}
