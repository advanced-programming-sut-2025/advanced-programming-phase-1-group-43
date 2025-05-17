package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class User implements Serializable {
    public static int Energy;
    public static int FishingSkill;
    private Farm farm = new Farm(); // each user has a farm

    private static final long serialVersionUID = 1L;
    private String username;
    private String nickname;
    private String passwordHash;
    private String email;
    private String gender;
    private String securityQuestion;
    private String securityAnswerHash;
    public static int XLoc;
    public static int YLoc;
    public static int Water;
    public static ArrayList<Object> OwnGhafasiAnimals = new ArrayList<>();
    public static ArrayList<Object> OwnTavileiiAnimals = new ArrayList<>();
    public static ArrayList<Object> OwnFishs = new ArrayList<>();
    // Game stats:
    private int highestGold;
    private int gamesPlayed;

    int X, Y;


    public User(String username,
                String nickname,
                String passwordHash,
                String email,
                String gender,
                String securityQuestion,
                String securityAnswerHash) {
        this.username = username;
        this.nickname = nickname;
        this.passwordHash = passwordHash;
        this.email = email;
        this.gender = gender;
        this.securityQuestion = securityQuestion;
        this.securityAnswerHash = securityAnswerHash;
        this.highestGold = 0;
        this.gamesPlayed = 0;
    }

    public User() {
        this.gender = null;
    }

    // Getters & setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getNickname() { return nickname; }
    public void setNickname(String nickname) { this.nickname = nickname; }

    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getGender() { return gender; }

    public String getSecurityQuestion() { return securityQuestion; }
    public void setSecurityQuestion(String securityQuestion) { this.securityQuestion = securityQuestion; }

    public String getSecurityAnswerHash() { return securityAnswerHash; }
    public void setSecurityAnswerHash(String securityAnswerHash) { this.securityAnswerHash = securityAnswerHash; }

    public int getHighestGold() { return highestGold; }
    public int getGamesPlayed() { return gamesPlayed; }

    public Farm getFarm() {
        return farm;
    }

    public void recordGame(int goldEarned) {
        gamesPlayed++;
        if (goldEarned > highestGold) {
            highestGold = goldEarned;
        }
    }

    public void resetDaily() {
        // placeholder for daily resets (e.g. energy, daily quests)
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(username, user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", nickname='" + nickname + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", highestGold=" + highestGold +
                ", gamesPlayed=" + gamesPlayed +
                '}';
    }

    public Collection<String> getInventory() {
        return java.util.Collections.emptyList();
    }

    public void placeMachine(String fishSmoker, int i, int i1) {
    }

    public void setPosition(int x, int y) {
        X = x;
        Y = y;
    }

    public int getGold() {
        return highestGold;
    }

    public void spendGold(int price) {
    }

    public void queueSale(int total) {
    }

    public void addGold(int amount) {
    }
}
