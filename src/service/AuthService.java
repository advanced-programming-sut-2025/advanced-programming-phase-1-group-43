package service;

import Model.User;
import repository.UserRepository;
import util.EmailValidator;
import util.HashUtil;

import java.security.SecureRandom;
import java.util.*;

public class AuthService {
    private final UserRepository userRepo;

    public AuthService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public static final List<String> SECURITY_QUESTIONS =
            Collections.unmodifiableList(Arrays.asList(
                    "What is your grandmother's name?",
                    "What is the name of your first school?",
                    "What was the name of your childhood pet?"
            ));

    private enum RecoveryStage { NONE, AWAITING_ANSWER, AWAITING_NEW_PASSWORD }
    private RecoveryStage recoveryStage = RecoveryStage.NONE;
    private String recoveringUsername;

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

        if ("random".equals(p1)) {
            // تولید و نمایش پسورد تصادفی
            String rp;
            Scanner sc = new Scanner(System.in);
            while (true) {
                rp = generateStrongPassword();
                System.out.println("Generated random password: " + rp);
                System.out.print("Choose: (1) accept  (2) regenerate: ");
                String choice = sc.nextLine().trim();
                if ("1".equals(choice)) break;
            }
            p1 = rp;
            p2 = rp;
        }

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
        userRepo.setCurrent(user);
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
        if (username == null || pw == null) {
            throw new Exception("Username/password required");
        }
        User user = userRepo.findByUsername(username);
        if (user == null) {
            throw new Exception("Username not found");
        }
        if (!user.getPasswordHash().equals(HashUtil.sha256(pw))) {
            throw new Exception("Incorrect password");
        }

        if (stay) {
            util.SessionManager.save(username);
        }
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
        recoveryStage = RecoveryStage.AWAITING_ANSWER;
        recoveringUsername = username;
    }

    public void answerRecovery(String command) {
        if (recoveryStage != RecoveryStage.AWAITING_ANSWER) {
            System.out.println("No password recovery in progress.");
            return;
        }
        String[] p = command.split(" ", 3);
        if (p.length != 3 || !p[0].equals("answer") || !p[1].equals("-a")) {
            System.out.println("Usage: answer -a <answer>");
            return;
        }
        String ans = p[2];
        User user = userRepo.findByUsername(recoveringUsername);
        if (!user.getSecurityAnswerHash().equals(HashUtil.sha256(ans))) {
            System.out.println("Wrong answer. Returning to login menu.");
            recoveryStage = RecoveryStage.NONE;
            return;
        }
        // حالا نوبت گرفتن پسورد جدید
        Scanner sc = new Scanner(System.in);
        System.out.print("New password: ");
        String np = sc.nextLine().trim();
        System.out.print("Confirm password: ");
        String nc = sc.nextLine().trim();
        if (!np.equals(nc)) {
            System.out.println("Password confirmation does not match. Returning to login menu.");
            recoveryStage = RecoveryStage.NONE;
            return;
        }
        try {
            // ← اینجا به جای register(...) این‌کار را می‌کنیم:
            user.setPasswordHash(HashUtil.sha256(np));
            userRepo.update(user);
            System.out.println("Password reset successful.");
        } catch (Exception e) {
            System.out.println("Reset failed: " + e.getMessage());
        } finally {
            recoveryStage = RecoveryStage.NONE;
        }
    }

    public void updateProfile(String command) throws Exception {
        String[] p = command.split(" ");
        User u = userRepo.getCurrent();
        switch (p[1]) {
            case "username":
                if (userRepo.findByUsername(p[3])!=null)
                    throw new Exception("Username exists");
                u.setUsername(p[3]); break;
            case "nickname":
                String newNickname = "";
                for (int i = 3; i < p.length; i++)
                    newNickname += p[i] + " ";
                u.setNickname(newNickname); break;
            case "email":
                String newEmail = p[3];
                if (!EmailValidator.isValid(newEmail)) throw new Exception("Invalid email");
                u.setEmail(newEmail); break;
            case "password":
                String newPw=p[3], oldPw=p[5];

                if (!u.getPasswordHash().equals(HashUtil.sha256(oldPw))) {throw new Exception("Old password wrong");}
                if (newPw.length() < 8) {throw new Exception("Weak new password: must be at least 8 characters");}
                if (!newPw.matches(".*[A-Z].*")) {throw new Exception("Weak new password: must contain at least one uppercase letter");}
                if (!newPw.matches(".*[a-z].*")) {throw new Exception("Weak new password: must contain at least one lowercase letter");}
                if (!newPw.matches(".*\\d.*")) {throw new Exception("Weak new password: must contain at least one digit");}
                if (!newPw.matches(".*[^A-Za-z0-9].*")) {throw new Exception("Weak new password: must contain at least one special character");}

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

    // ابتدای کلاس، متد جدید برای pick question:
    public void pickSecurityQuestion(String command) throws Exception {
        // form: pick question -q <num> -a <ans> -c <ansConfirm>
        String[] p = command.split(" ");
        if (p.length!=8 || !p[0].equals("pick")||!p[1].equals("question")||!p[2].equals("-q")
                ||!p[4].equals("-a")||!p[6].equals("-c")) {
            throw new Exception("Usage: pick question -q <num> -a <answer> -c <confirm>");
        }
        int qnum = Integer.parseInt(p[3]) - 1;
        if (qnum<0 || qnum>=SECURITY_QUESTIONS.size()) throw new Exception("Invalid question number");

        String answer = p[5], confirm = p[7];  // p[6] = confirm flag, p[7] = answer_confirm
        if (!answer.equals(confirm)) throw new Exception("Answer confirmation does not match");

        User u = userRepo.getCurrent();
        u.setSecurityQuestion(SECURITY_QUESTIONS.get(qnum));
        u.setSecurityAnswerHash(HashUtil.sha256(answer));
        userRepo.update(u);
        System.out.println("Security question set.");
    }

    private static final int MIN_PW_LEN = 8;
    private static final int MAX_PW_LEN = 14;
    private final Random rnd = new SecureRandom();

    /**
     * تولید پسورد قویِ تصادفی با طول تصادفی بین MIN_PW_LEN و MAX_PW_LEN
     */
    public String generateStrongPassword() {
        int length = MIN_PW_LEN + rnd.nextInt(MAX_PW_LEN - MIN_PW_LEN + 1);
        String upper   = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lower   = "abcdefghijklmnopqrstuvwxyz";
        String digits  = "0123456789";
        String special = "!@#$%^&*()";
        String all = upper + lower + digits + special;
        List<Character> pwd = new ArrayList<>(length);
        // تضمین حداقل یک کاراکتر از هر دسته
        pwd.add(upper.charAt(rnd.nextInt(upper.length())));
        pwd.add(lower.charAt(rnd.nextInt(lower.length())));
        pwd.add(digits.charAt(rnd.nextInt(digits.length())));
        pwd.add(special.charAt(rnd.nextInt(special.length())));
        // بقیه را از کل مجموعه
        for (int i = 4; i < length; i++) {
            pwd.add(all.charAt(rnd.nextInt(all.length())));
        }
        Collections.shuffle(pwd, rnd);
        StringBuilder sb = new StringBuilder();
        for (char c : pwd) sb.append(c);
        return sb.toString();
    }
}