package Controller;

import Model.enums.WeatherType;
import service.TimeService;
import service.WeatherService;

public class WeatherController {
    private final WeatherService weather;
    private final TimeService time;

    public WeatherController(WeatherService weather, TimeService time) {
        this.weather = weather;
        this.time = time;
    }

    public void handle(String cmd) {
        if (cmd.equals("weather")) System.out.println(weather.getCurrent());
        else if (cmd.equals("weather forecast")) System.out.println(weather.getForecast());
        else if (cmd.startsWith("cheat weather set ")) {
            WeatherType w = WeatherType.valueOf(cmd.substring(17).toUpperCase());
            weather.cheatSetWeatherType(w);
            System.out.println("Forecast set to " + w);
        }
        else System.out.println("Invalid weather command");
    }
}
