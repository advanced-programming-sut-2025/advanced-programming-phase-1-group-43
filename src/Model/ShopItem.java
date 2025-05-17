package Model;

import Model.enums.SeasonEnum;

public class ShopItem {
    private final String name;
    private final int basePrice;
    private final SeasonEnum[] seasons;
    private final int dailyLimit;
    private int purchased;

    public ShopItem(String n,int price,SeasonEnum[] s,int limit){name=n;basePrice=price;seasons=s;dailyLimit=limit;purchased=0;}
    public boolean isAvailable(SeasonEnum s){for(SeasonEnum ss:seasons) if(ss==s)return true;return false;}
    public int remaining(){return dailyLimit-purchased;}
    public int getDailyLimit(){return dailyLimit;}
    public void purchase(int c){purchased+=c;}
    public int priceFor(SeasonEnum s){return (int)(basePrice*SeasonalPricing.adjust(name,s));}
    public String fullInfo(){return name+" - " + basePrice + "g ("+dailyLimit+"/day)";}
    public String availableInfo(){return name+" - " + basePrice + "g ("+remaining()+" left)";}
}
