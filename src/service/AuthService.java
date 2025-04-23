package service;

import Model.User;
import repository.UserRepository;
import util.EmailValidator;
import util.HashUtil;

import java.util.*;

public class AuthService {
    private final UserRepository userRepo;

    public AuthService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public void register(String command) throws Exception {
        String[] parts = command.split(" ");
        Map<String,String> args = new HashMap<>();
        List<String> extras = new ArrayList<>();
        for (int i = 1; i < parts.length; i++) {
            if (parts[i].startsWith("-")) {
                String key = parts[i];
                if (i+1<parts.length && !parts[i+1].startsWith("-")) {
                    args.put(key, parts[++i]);
                } else {
                    args.put(key, null);
                }
            } else {
                extras.add(parts[i]);
            }
        }
        String username = args.get("-u");
        String p1 = args.get("-p");
        String p2 = extras.size()>0 ? extras.get(0) : null;
        String nickname = args.get("-n");
        String email = args.get("-e");
        String gender = args.get("-g");

        // validate username
        if (username==null || !username.matches("[A-Za-z0-9-]+"))
            throw new Exception("Invalid username");
        if (userRepo.findByUsername(username)!=null)
            throw new Exception("Username already exists");

        // validate passwords
        if (p1==null || p2==null || !p1.equals(p2))
            throw new Exception("Passwords do not match");
        if (p1.length()<8
                || !p1.matches(".*[A-Z].*")
                || !p1.matches(".*[a-z].*")
                || !p1.matches(".*\\d.*")
                || !p1.matches(".*[^A-Za-z0-9].*"))
            throw new Exception("Password too weak");

        // validate email
        if (email==null || !EmailValidator.isValid(email))
            throw new Exception("Invalid email");

        // hash password
        String hash = HashUtil.sha256(p1);

        // create & save user
        User user = new User(username, nickname, hash, email, gender, null, null);
        userRepo.save(user);
    }

    public void login(String command) throws Exception {
        String[] parts = command.split(" ");
        String username=null, pw=null;
        boolean stay=false;
        for (int i=1;i<parts.length;i++){
            if ("-u".equals(parts[i]) && i+1<parts.length) username=parts[++i];
            else if ("-p".equals(parts[i]) && i+1<parts.length) pw=parts[++i];
            else if ("--stay-logged-in".equals(parts[i])) stay=true;
        }
        if (username==null||pw==null) throw new Exception("Username/password required");

        User user = userRepo.findByUsername(username);
        if (user==null) throw new Exception("Username not found");
        if (!user.getPasswordHash().equals(HashUtil.sha256(pw)))
            throw new Exception("Incorrect password");

        userRepo.setCurrent(user);
        // TODO: handle 'stay' flag (e.g. write to disk)
    }

    public void initiatePasswordRecovery(String command) {
        String[] parts = command.split(" ");
        String username = null;
        for (int i = 1; i < parts.length; i++)
            if ("-u".equals(parts[i]) && i + 1 < parts.length) username = parts[++i];

        User user = userRepo.findByUsername(username);
        if (user == null) {
            System.out.println("No such user");
            return;
        }

        System.out.println("Question: " + user.getSecurityQuestion());
        Scanner sc = new Scanner(System.in);
        System.out.print("answer: ");
        String ans = sc.nextLine();
        if (!user.getSecurityAnswerHash().equals(HashUtil.sha256(ans))) {
            System.out.println("Wrong answer");
            return;
        }

        System.out.print("New password: ");
        String np = sc.nextLine();
        System.out.print("Confirm: ");
        String nc = sc.nextLine();
        try {
            register("register -u " + username + " -p " + np + " " + nc + " -n " + user.getNickname() + " -e " + user.getEmail() + " -g " + user.getGender());
            System.out.println("Password reset.");
        } catch (Exception e) {
            System.out.println("Reset failed: " + e.getMessage());
        }
    }

    public void updateProfile(String command) throws Exception {
        String[] p = command.split(" ");
        User u = userRepo.getCurrent();
        switch (p[1]) {
            case "username":
                if (userRepo.findByUsername(p[2])!=null) throw new Exception("Username exists");
                u.setUsername(p[2]); break;
            case "nickname":
                u.setNickname(p[2]); break;
            case "email":
                if (!EmailValidator.isValid(p[2])) throw new Exception("Invalid email");
                u.setEmail(p[2]); break;
            case "password":
                String newPw=p[2], oldPw=p[4];
                if (!u.getPasswordHash().equals(HashUtil.sha256(oldPw)))
                    throw new Exception("Old password wrong");
                if (newPw.length()<8) throw new Exception("Weak new password");
                u.setPasswordHash(HashUtil.sha256(newPw));
                break;
            default:
                throw new Exception("Unknown field");
        }
        userRepo.update(u);
    }

    public String getCurrentUserInfo() {
        User u = userRepo.getCurrent();
        return String.format(
                "Username: %s%nNickname: %s%nHighest Gold: %d%nGames Played: %d",
                u.getUsername(), u.getNickname(), u.getHighestGold(), u.getGamesPlayed()
        );
    }
}