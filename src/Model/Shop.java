package Model;

import Model.enums.SeasonEnum;
import java.time.LocalTime;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;

public class Shop {
    private final String name;
    private final int openHour, closeHour;
    private final Map<String, ShopItem> stock;

    public Shop(String name, int open, int close, Map<String, ShopItem> stock) {
        this.name = name;
        this.openHour = open;
        this.closeHour = close;
        this.stock = new LinkedHashMap<>(stock);
    }
    public String getName(){return name;}
    public boolean isOpen(LocalTime t){int h=t.getHour();return h>=openHour&&h<closeHour;}
    public String listAll(){return stock.values().stream().map(ShopItem::fullInfo).collect(Collectors.joining("\n"));}
    public String listAvailable(SeasonEnum s){return stock.values().stream().filter(i->i.isAvailable(s)&&i.remaining()>0).map(ShopItem::availableInfo).collect(Collectors.joining("\n"));}
    public boolean hasItem(String n){return stock.containsKey(n);}
    public boolean inSeason(String n,SeasonEnum s){return stock.get(n).isAvailable(s);}
    public int dailyLimit(String n){return stock.get(n).getDailyLimit();}
    public int price(String n,SeasonEnum s){return stock.get(n).priceFor(s);}
    public void recordPurchase(String n,int c){stock.get(n).purchase(c);}
}
