package Controller.MenuControllers;

import repository.UserRepository;
import util.SessionManager;

public class MainMenuController {
    private final UserRepository userRepo;

    public MainMenuController(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public void handle(String input) {
        switch (input) {
            case "user logout":
                // پاک کردن session.json
                try {
                    SessionManager.clear();
                } catch (Exception e) {
                    System.err.println("Could not clear session: " + e.getMessage());
                }
                // خروج کاربر از حالت لاگین
                userRepo.setCurrent(null);

                System.out.println("Logged out.");
                break;
            default:
                System.out.println("Unknown command in main menu");
        }
    }
}
