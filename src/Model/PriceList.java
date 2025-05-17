package Model;

import java.util.Map;
import java.util.HashMap;

public class PriceList {
    private static final Map<String,Integer> salePrices = new HashMap<>();
    static {
        salePrices.put("Parsnip", 35);
        salePrices.put("Potato", 80);
        // ... other base sale prices
    }
    public static int getSalePrice(String item){
        return salePrices.getOrDefault(item,50);
    }
}