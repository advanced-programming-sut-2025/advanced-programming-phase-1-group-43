package Controller.MenuControllers;

import service.AuthService;

import java.util.List;
import java.util.Scanner;

public class RegistrationMenuController {
    private final AuthService auth;
    private final Scanner sc = new Scanner(System.in);

    public RegistrationMenuController(AuthService auth) { this.auth = auth; }

    public void handle(String input) {
        if (input.startsWith("register ")) {
            try {
                auth.register(input);
                System.out.println("Registration successful.");

                // نمایش سؤال‌های امنیتی
                System.out.println("Select a security question:");
                List<String> qs = AuthService.SECURITY_QUESTIONS;
                for (int i=0; i<qs.size(); i++) {
                    System.out.printf("%d) %s%n", i + 1, qs.get(i));
                }
                System.out.println("pick question -q <number> -a <answer> -c <confirm>");
            } catch (Exception ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        }
        else if (input.startsWith("pick question")) {
            try {
                auth.pickSecurityQuestion(input);
            } catch (Exception ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        }
        else {
            System.out.println("Invalid command in register menu");
        }
    }
}
