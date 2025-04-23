package Controller.MenuControllers;

import service.AuthService;

public class ProfileMenuController {
    private final AuthService auth;
    public ProfileMenuController(AuthService auth) { this.auth = auth; }

    public void handle(String input) {
        if (input.startsWith("change ")) {
            try { auth.updateProfile(input); System.out.println("Updated."); }
            catch (Exception ex) { System.out.println("Error: " + ex.getMessage()); }
        } else if ("user info".equals(input)) {
            System.out.println(auth.getCurrentUserInfo());
        } else {
            System.out.println("Invalid command in profile menu");
        }
    }
}
