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
                String msg = ex.getMessage();
                System.out.println("Error: " + msg);
                // فقط در صورت "Incorrect password" اجازه فراموشی بده
                if ("Incorrect password".equals(msg)) {
                    System.out.print("Forgot password? (y/n): ");
                    if ("y".equalsIgnoreCase(sc.nextLine())) {
                        System.out.print("Enter: forget password -u <username>>\n");
                        String cmd = sc.nextLine();
                        auth.initiatePasswordRecovery(cmd);
                    }
                }
            }
        }
        else if (input.startsWith("forget password ")) {
            auth.initiatePasswordRecovery(input);
        }
        else if (input.startsWith("answer -a ")) {
            auth.answerRecovery(input);
        }
        else {
            System.out.println("Invalid command in login menu");
        }
    }
}
