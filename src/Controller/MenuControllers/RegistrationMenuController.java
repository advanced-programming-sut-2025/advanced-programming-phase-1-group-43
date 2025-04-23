package Controller.MenuControllers;

import service.AuthService;

import java.util.Scanner;

public class RegistrationMenuController {
    private final AuthService auth;
    private final Scanner sc = new Scanner(System.in);

    public RegistrationMenuController(AuthService auth) { this.auth = auth; }

    public void handle(String input) {
        if (!input.startsWith("register ")) {
            System.out.println("Invalid command in register menu"); return;
        }
        try {
            // parse: register -u <user> -p <pw> <pw2> -n <nick> -e <email> -g <gender>
            auth.register(input);
            System.out.println("Registration successful.");
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
}
