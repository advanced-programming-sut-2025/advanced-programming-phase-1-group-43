import Controller.MenuControllers.*;
import repository.GameRepository;
import repository.UserRepository;
import service.AuthService;
import service.GameService;

public class Main {
    public static void main(String[] args) {
        try {
            // 1. create repositories
            UserRepository userRepo = new UserRepository();
            GameRepository gameRepo = new GameRepository(userRepo);

            // 2. create services
            AuthService authService = new AuthService(userRepo);
            GameService gameService = new GameService(gameRepo);

            // 3. create controllers
            RegistrationMenuController regCtrl = new RegistrationMenuController(authService);
            LoginMenuController      loginCtrl = new LoginMenuController(authService);
            MainMenuController       mainCtrl  = new MainMenuController();
            ProfileMenuController    profCtrl  = new ProfileMenuController(authService);
            GameMenuController       gameCtrl  = new GameMenuController(gameService);

            // 4. create and start menu router
            MenuRouter router = new MenuRouter(regCtrl, loginCtrl, mainCtrl, profCtrl, gameCtrl);
            router.loop();

        } catch (Exception e) {
            System.err.println("Fatal error during startup: " + e.getMessage());
            e.printStackTrace();
        }
    }
}