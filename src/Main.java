import Controller.MenuControllers.*;
import Controller.TimeController;
import Controller.WeatherController;
import repository.GameRepository;
import repository.UserRepository;
import service.AuthService;
import service.GameService;
import service.TimeService;
import service.WeatherService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);

            // 1. create repositories
            UserRepository userRepo = new UserRepository();
            GameRepository gameRepo = new GameRepository(userRepo);

            // 2. create services
            AuthService authService = new AuthService(userRepo);
            GameService gameService = new GameService(gameRepo);
            TimeService timeService = new TimeService();
            WeatherService weatherService = new WeatherService();

            // 3. create controllers
            RegistrationMenuController regCtrl = new RegistrationMenuController(authService);
            LoginMenuController loginCtrl = new LoginMenuController(authService);
            MainMenuController mainCtrl = new MainMenuController();
            ProfileMenuController profCtrl = new ProfileMenuController(authService);
            TimeController timeCtrl = new TimeController(timeService);
            WeatherController weatherCtrl = new WeatherController(weatherService, timeService);
            GameMenuController gameCtrl = new GameMenuController(gameService, timeCtrl, weatherCtrl);

            // 4. create and start menu router
            MenuRouter router = new MenuRouter(regCtrl, loginCtrl, mainCtrl, profCtrl, gameCtrl, scanner);
            router.loop();

        } catch (Exception e) {
            System.err.println("Fatal error during startup: " + e.getMessage());
            e.printStackTrace();
        }
    }
}