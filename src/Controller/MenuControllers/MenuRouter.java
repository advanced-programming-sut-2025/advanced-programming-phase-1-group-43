package Controller.MenuControllers;

import repository.UserRepository;

import java.util.Scanner;

public class MenuRouter {
    private String currentMenu = null;
    private final RegistrationMenuController regCtrl;
    private final LoginMenuController loginCtrl;
    private final MainMenuController mainCtrl;
    private final ProfileMenuController profileCtrl;
    private final GameMenuController gameCtrl;
    private final Scanner scanner;
    private final UserRepository userRepo;

    public MenuRouter(RegistrationMenuController regCtrl,
                      LoginMenuController loginCtrl,
                      MainMenuController mainCtrl,
                      ProfileMenuController profileCtrl,
                      GameMenuController gameCtrl,
                      Scanner scanner,
                      UserRepository userRepo) {
        this.regCtrl = regCtrl;
        this.loginCtrl = loginCtrl;
        this.mainCtrl = mainCtrl;
        this.profileCtrl = profileCtrl;
        this.gameCtrl = gameCtrl;
        this.scanner = scanner;
        this.userRepo = userRepo;
    }

    public void loop() {
        if (userRepo.getCurrent() != null) {
            currentMenu = "main";
            System.out.println("Welcome back, " + userRepo.getCurrent().getUsername() + "! You are still logged in.");
        } else {
            while (true) {
                System.out.println("Choose your menu: [enter login or register]");
                currentMenu = scanner.nextLine();
                if (currentMenu.equals("login") || currentMenu.equals("register")) {
                    break;
                }
                else {
                    System.out.println("please choose one of the following options: login or register");
                }
            }
        }

        while (true) {
            System.out.println(currentMenu + ">");
            String line = scanner.nextLine().trim();
            if (currentMenu.equals("login") && line.startsWith("login ")) {
                // special: after login, switch to main
                loginCtrl.handle(line);
                if (userRepo.getCurrent() != null) {
                    currentMenu = "main";
                }
                continue;
            }
            if (line.equals("show current menu")) {
                System.out.println("Current menu: " + currentMenu);
            } else if (line.startsWith("menu ")) {
                handleMenuCommand(line);
            } else {
                dispatchToCurrent(line);
            }
        }
    }

    private void handleMenuCommand(String line) {
        String[] parts = line.split(" ", 3);
        String cmd = parts[1];
        switch (cmd) {
            case "enter":
                if (parts.length<3) {
                    System.out.println("To access a menu, you must use the command \"menu enter <menu_name>\"."); return;
                }
                enterMenu(parts[2]);
                break;
            case "exit":
                if (currentMenu.equals("register") || currentMenu.equals("login")) {
                    System.out.println("The program has ended.");
                    System.exit(0);
                } else {
                    leaveMenu();
                }
                break;
            default:
                System.out.println("Unknown menu command");
        }
    }

    private void enterMenu(String menu) {
        if (isValidTransition(menu)) {
            currentMenu = menu;
            System.out.println("You have successfully entered " + menu + " menu.");
        } else {
            System.out.println("Cannot enter menu '"+menu+"' from '"+currentMenu+"'.");
        }
    }

    private void leaveMenu() {
        String lastMenu = currentMenu;
        switch (currentMenu) {
            case "main": currentMenu = "login";
                currentMenu = "login";
                userRepo.setCurrent(null);
                // پاک کردن session file
                try {
                    util.SessionManager.clear();
                } catch (Exception e) {
                    System.err.println("Warning: failed to clear session: " + e.getMessage());
                }
                break;
            case "profile": currentMenu = "main"; break;
            case "game": currentMenu = "main"; break;
            default: System.out.println("Cannot exit from '"+currentMenu+"'.");
        }
        System.out.println("You have exited " + lastMenu + " menu and entered " + currentMenu + " menu.");
    }

    private boolean isValidTransition(String target) {
        switch (currentMenu) {
            case "register": case "login":
                return target.equals("login") || target.equals("register");
            case "main":
                return target.equals("profile") || target.equals("game") || target.equals("login") || target.equals("register");
            case "profile":
                return target.equals("main");
            case "game":
                return target.equals("main");
            default:
                return false;
        }
    }

    private void dispatchToCurrent(String line) {
        if (currentMenu.equals("register") && line.startsWith("pick question")) {
            // اجرا کن
            regCtrl.handle(line);
            // اگر کاربر جاری سؤال امنیتی انتخاب کرده، می‌رویم main
            if (userRepo.getCurrent()!=null
                    && userRepo.getCurrent().getSecurityQuestion()!=null) {
                currentMenu = "main";
                System.out.println("Automatically entered main menu.");
            }
            return;
        }
        if (currentMenu.equals("login") && (line.startsWith("forget password") || line.startsWith("answer -a"))) {
            loginCtrl.handle(line);
            return;
        }

        // global commands:
        if (line.startsWith("time") || line.startsWith("weather")) {
            // forward into gameCtrl which knows how to handle them
            gameCtrl.handle(line);
            return;
        }

        switch (currentMenu) {
            case "register": regCtrl.handle(line); break;
            case "login":    loginCtrl.handle(line); break;
            case "main":     mainCtrl.handle(line); break;
            case "profile":  profileCtrl.handle(line); break;
            case "game":     gameCtrl.handle(line); break;
            default:          System.out.println("Unknown menu state");
        }
    }
}

