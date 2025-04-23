package Controller.MenuControllers;

public class MainMenuController {
    public void handle(String input) {
        switch (input) {
            case "user logout":
                System.out.println("Logged out.");
                break;
            default:
                System.out.println("Unknown command in main menu");
        }
    }
}
