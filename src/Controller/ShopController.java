package Controller;

import Model.*;
import Model.enums.SeasonEnum;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Manages shop interaction: enter, list, purchase, sell, cheat add.
 */
public class ShopController {
    private final User user;
    private final Map<String, Shop> shops = new HashMap<>();
    private Shop current;

    public ShopController(User user) {
        this.user = user;
        // register shops
        shops.put("Blacksmith", ShopFactory.createBlacksmith());
        shops.put("JojaMart", ShopFactory.createJojaMart());
        shops.put("Pierre", ShopFactory.createPierre());
        shops.put("Carpenter", ShopFactory.createCarpenter());
        shops.put("FishShop", ShopFactory.createFishShop());
        shops.put("Marnie", ShopFactory.createMarnie());
        shops.put("Saloon", ShopFactory.createSaloon());
    }

    public String enter(String name) {
        Shop s = shops.get(name);
        if (s == null) return "No such shop: " + name;
        LocalTime now = user.getFarm().getCurrentTime();
        if (!s.isOpen(now)) return name + " is closed";
        current = s;
        return "Entered " + name;
    }

    public String exit() {
        if (current == null) return "Not in a shop";
        String msg = "Exited " + current.getName();
        current = null;
        return msg;
    }

    public String showAllProducts() {
        if (current == null) return "Not in a shop";
        return current.listAll();
    }

    public String showAvailableProducts() {
        if (current == null) return "Not in a shop";
        SeasonEnum season = user.getFarm().getSeason();
        return current.listAvailable(season);
    }

    public String purchase(String item, int count) {
        if (current == null) return "Not in a shop";
        SeasonEnum season = user.getFarm().getSeason();
        if (!current.hasItem(item)) return item + " not sold here";
        if (!current.inSeason(item, season)) return item + " not sold this season";
        int limit = current.dailyLimit(item);
        if (count > limit) return "Daily limit exceeded for " + item;
        int price = current.price(item, season) * count;
        if (user.getGold() < price) return "Not enough gold";
        user.spendGold(price);
        user.getFarm().getInventory().addItem(item, count);
        current.recordPurchase(item, count);
        return "Purchased " + count + " " + item;
    }

    public String sell(String item, int count) {
        if (current != null) return "Cannot sell inside a shop";
        Inventory inv = user.getFarm().getInventory();
        if (!inv.hasItem(item, count)) return "You don't have enough " + item;
        if (!user.getFarm().isAdjacentToBin()) return "Not at shipping bin";
        inv.removeItem(item, count);
        int basePrice = PriceList.getSalePrice(item);
        double multiplier = Quality.getQualityMultiplier(item);
        int total = (int)(basePrice * multiplier) * count;
        user.queueSale(total);
        return "Sold " + count + " " + item + ". Gold will arrive tomorrow.";
    }

    public String cheatAdd(int amount) {
        user.addGold(amount);
        return "Added " + amount + " gold";
    }
}