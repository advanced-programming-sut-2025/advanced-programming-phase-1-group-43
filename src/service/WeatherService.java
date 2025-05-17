package service;

import Model.enums.SeasonEnum;
import Model.enums.WeatherType;

import java.util.EnumMap;
import java.util.Map;
import java.util.Random;

public class WeatherService {
    private WeatherType current;
    private WeatherType forecast;
    private final Random rnd = new Random();
    private boolean forecastWasCheated = false;

    private static final Map<SeasonEnum, double[]> PROBS = new EnumMap<>(SeasonEnum.class);
    static {
        // order: SUNNY, RAIN, STORM, SNOW
        PROBS.put(SeasonEnum.SPRING, new double[]{0.5, 0.3, 0.15, 0.05});
        PROBS.put(SeasonEnum.SUMMER, new double[]{0.6, 0.25,0.1,  0.05});
        PROBS.put(SeasonEnum.FALL,   new double[]{0.5, 0.3, 0.15, 0.05});
        PROBS.put(SeasonEnum.WINTER, new double[]{0.7, 0.1, 0.05, 0.15});
    }

    public WeatherService() {
        current = WeatherType.SUNNY;
        forecast = WeatherType.SUNNY;
    }

    public WeatherType getCurrent() {
        return current;
    }

    public WeatherType getForecast() {
        return forecast;
    }

    // called at day start
    public void rollForecast(String seasonName) {
        if (forecastWasCheated) {
            forecastWasCheated = false;  // ✅ use the cheated value once
            return;
        }

        SeasonEnum s = SeasonEnum.valueOf(seasonName);
        double[] ps = PROBS.get(s);
        double r = rnd.nextDouble();
        double cum = 0;
        for(int i=0;i<ps.length;i++){
            cum += ps[i];
            if(r <= cum){
                forecast = WeatherType.values()[i];
                return;
            }
        }
        forecast = WeatherType.SUNNY;
    }

    public void advanceToNextDay() {
        current = forecast;
    }

    public void cheatSetWeatherType(WeatherType w) {
        forecast = w;
        forecastWasCheated = true;
    }

    public void applyEffects() {
        switch(current){
            case SUNNY: break;
            case RAIN:
                // auto-irrigate, half-tool energy cost, …
                break;
            case STORM:
                // lightning strikes
                break;
            case SNOW:
                // double energy cost, …
                break;
        }
    }
}
