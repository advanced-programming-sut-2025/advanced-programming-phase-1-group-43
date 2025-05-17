package Model;

import Model.enums.SeasonEnum;

public class SeasonalPricing {
    public static double adjust(String item, SeasonEnum season){
        // example: Pierre charges 1.1x in summer for certain items
        return 1.0;
    }
}