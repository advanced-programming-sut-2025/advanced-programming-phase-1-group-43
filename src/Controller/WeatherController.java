package Controller;

import Model.enums.WeatherType;
import service.WeatherService;

import java.util.Arrays;
import java.util.stream.Collectors;

public class WeatherController {
    private static final String CHEAT_PREFIX = "cheat weather set ";
    private final WeatherService weather;

    public WeatherController(WeatherService weather) {
        this.weather = weather;
    }

    public void handle(String cmd) {
        if (cmd.equals("weather")) {
            System.out.println("Today’s weather: " + weather.getCurrent());
        }
        else if (cmd.equals("weather forecast")) {
            System.out.println("Tomorrow’s forecast: " + weather.getForecast());
        }
        else if (cmd.startsWith(CHEAT_PREFIX)) {
            // extract everything after our prefix:
            String token = cmd.substring(CHEAT_PREFIX.length()).trim().toUpperCase();
            try {
                WeatherType w = WeatherType.valueOf(token);
                weather.cheatSetWeatherType(w);
                System.out.println("Forecast set to " + w +
                        " (today remains " + weather.getCurrent() + ")");
            } catch (IllegalArgumentException e) {
                String valid = Arrays.stream(WeatherType.values())
                        .map(Enum::name)
                        .collect(Collectors.joining(", "));
                System.out.println("Error: invalid weather type '" + token +
                        "'. Valid types are: " + valid + ".");
            }
        }
        else {
            System.out.println("Invalid weather command");
        }
    }
}
