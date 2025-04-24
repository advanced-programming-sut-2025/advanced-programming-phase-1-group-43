package service;

import Model.User;
import Model.enums.WeatherType;

import java.util.Random;

public class WeatherService {
    private WeatherType current;
    private WeatherType forecast;
    private final Random rnd = new Random();

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
    public void rollForecast(String season) {
        // choose forecast based on season probabilities
        WeatherType[] vals = WeatherType.values();
        forecast = vals[rnd.nextInt(vals.length)];
    }

    public void advanceToNextDay() {
        current = forecast;
    }

    public void cheatSetWeatherType(WeatherType w) {
        forecast = w;
    }

    public void applyEffects(User user) {
        switch (current) {
            case SUNNY: break;
            case RAIN:
                user.getFarm().autoIrrigate();
                break;
            case STORM:
                user.getFarm().autoIrrigate();
                user.getFarm().strikeLightning();
                break;
            case SNOW:
                break;
        }
    }
}
