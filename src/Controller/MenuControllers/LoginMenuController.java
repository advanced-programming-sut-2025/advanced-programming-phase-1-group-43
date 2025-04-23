package Controller.MenuControllers;

import service.AuthService;

import java.util.Scanner;

public class LoginMenuController {
    private final AuthService auth;
    private final Scanner sc = new Scanner(System.in);

    public LoginMenuController(AuthService auth) { this.auth = auth; }

    public void handle(String input) {
        if (input.startsWith("login ")) {
            try {
                auth.login(input);
                System.out.println("Login successful.");
            } catch (Exception ex) {
                System.out.println("Error: " + ex.getMessage());
                System.out.print("Forgot password? (y/n): ");
                if ("y".equalsIgnoreCase(sc.nextLine())) {
                    auth.initiatePasswordRecovery(input);
                }
            }
        } else {
            System.out.println("Invalid command in login menu");
        }
    }
}
