package Controller;

import Model.Shop;
import Model.ShopItem;
import Model.enums.SeasonEnum;

import java.util.Map;
import java.util.HashMap;

public class ShopFactory {
    public static Shop createBlacksmith() {
        Map<String, ShopItem> stock = new HashMap<>();
        stock.put("Metal Bar", new ShopItem("Metal Bar", 300, new SeasonEnum[]{SeasonEnum.SPRING, SeasonEnum.SUMMER, SeasonEnum.FALL, SeasonEnum.WINTER}, 10));
        stock.put("Coal", new ShopItem("Coal", 150, SeasonEnum.values(), 20));
        return new Shop("Blacksmith", 9, 16, stock);
    }

    public static Shop createJojaMart() {
        Map<String, ShopItem> stock = new HashMap<>();
        stock.put("Wheat Seeds", new ShopItem("Wheat Seeds", 30, new SeasonEnum[]{SeasonEnum.SPRING, SeasonEnum.SUMMER}, 50));
        stock.put("Corn Seeds", new ShopItem("Corn Seeds", 150, new SeasonEnum[]{SeasonEnum.SUMMER, SeasonEnum.FALL}, 30));
        stock.put("Fertilizer", new ShopItem("Fertilizer", 100, SeasonEnum.values(), 40));
        return new Shop("JojaMart", 9, 23, stock);
    }

    public static Shop createPierre() {
        Map<String, ShopItem> stock = new HashMap<>();
        stock.put("Parsnip Seeds", new ShopItem("Parsnip Seeds", 20, new SeasonEnum[]{SeasonEnum.SPRING}, 100));
        stock.put("Potato Seeds", new ShopItem("Potato Seeds", 50, new SeasonEnum[]{SeasonEnum.SPRING}, 80));
        stock.put("Backpack Upgrade", new ShopItem("Backpack Upgrade", 2000, SeasonEnum.values(), 5));
        return new Shop("Pierre", 9, 17, stock);
    }

    public static Shop createCarpenter() {
        Map<String, ShopItem> stock = new HashMap<>();
        stock.put("Wood", new ShopItem("Wood", 10, SeasonEnum.values(), 999));
        stock.put("Stone", new ShopItem("Stone", 20, SeasonEnum.values(), 999));
        stock.put("Barn", new ShopItem("Barn", 6000, SeasonEnum.values(), 2));
        stock.put("Coop", new ShopItem("Coop", 4000, SeasonEnum.values(), 2));
        return new Shop("Carpenter", 9, 20, stock);
    }

    public static Shop createFishShop() {
        Map<String, ShopItem> stock = new HashMap<>();
        stock.put("Fishing Rod", new ShopItem("Fishing Rod", 500, SeasonEnum.values(), 10));
        stock.put("Bait", new ShopItem("Bait", 5, SeasonEnum.values(), 200));
        return new Shop("FishShop", 9, 17, stock);
    }

    public static Shop createMarnie() {
        Map<String, ShopItem> stock = new HashMap<>();
        stock.put("Chicken", new ShopItem("Chicken", 800, SeasonEnum.values(), 5));
        stock.put("Cow", new ShopItem("Cow", 1500, SeasonEnum.values(), 3));
        stock.put("Animal Feed", new ShopItem("Animal Feed", 50, SeasonEnum.values(), 100));
        return new Shop("Marnie", 9, 16, stock);
    }

    public static Shop createSaloon() {
        Map<String, ShopItem> stock = new HashMap<>();
        stock.put("Beer", new ShopItem("Beer", 300, SeasonEnum.values(), 20));
        stock.put("Spaghetti", new ShopItem("Spaghetti", 240, SeasonEnum.values(), 15));
        stock.put("Pizza", new ShopItem("Pizza", 300, SeasonEnum.values(), 10));
        return new Shop("Saloon", 12, 24, stock);
    }
}