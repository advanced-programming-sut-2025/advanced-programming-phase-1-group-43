package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private String username;
    private String nickname;
    private String passwordHash;
    private String email;
    private String gender;
    private String securityQuestion;
    private String securityAnswerHash;
    public int x;
    public int y;
    public double Energy;

    // Game stats:
    private int highestGold;
    private int gamesPlayed;

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
    public String getSecurityAnswerHash() { return securityAnswerHash; }
    public int getHighestGold() { return highestGold; }
    public int getGamesPlayed() { return gamesPlayed; }

    public void recordGame(int gold) {
        gamesPlayed++;
        if (gold > highestGold) highestGold = gold;
    }

    ///rah raftan
    public ArrayList<Object> CanWalk(int x, int y, int SecondX, int SecondY) {
        double Energyy = 0.0;

        while (x != SecondX || y != SecondY) {
            int isWalk = 0;

            if (x > SecondX) {
                if (!Farm.xLocations.contains(x - 1) && !Farm.yLocations.contains(y)) {
                    x--;
                    Energyy += 1.0 / 20.0;
                    isWalk = 1;
                }
            } else if (x < SecondX) {
                if (!Farm.xLocations.contains(x + 1) && !Farm.yLocations.contains(y)) {
                    x++;
                    Energyy += 1.0 / 20.0;
                    isWalk = 1;
                }
            }

            if (y > SecondY) {
                if (!Farm.yLocations.contains(y - 1) && !Farm.xLocations.contains(x)) {
                    y--;
                    Energyy += 1.0 / 20.0;
                    isWalk = 1;
                }
            } else if (y < SecondY) {
                if (!Farm.yLocations.contains(y + 1) && !Farm.xLocations.contains(x)) {
                    y++;
                    Energyy += 1.0 / 20.0;
                    isWalk = 1;
                }
            }

            if (Energyy == 0.0) {
                System.out.println("GHASH KARD");
                break;
            }

            if (isWalk == 0) {
                System.out.println("Can't walk");
                return new ArrayList<>(Arrays.asList(false, 0.0));
            }
        }

        return new ArrayList<>(Arrays.asList(true, Energyy));
    }

    public boolean MainWalk(int x, int y, int SecondX, int SecondY) {
        while(x == SecondX && y == SecondY) {
            int isWalk = 0;
            if (x > SecondX) {
                if(!Farm.xLocations.contains(x-1) && !Farm.yLocations.contains(y)) {
                    x --;
                    Energy -= 1/20;
                    isWalk = 1;
                }
            }else if(x < SecondX){
                if(!Farm.xLocations.contains(x+1) && !Farm.yLocations.contains(y)) {
                    x ++;
                    Energy += 1/20;
                    isWalk = 1;
                }
            }
            if(y > SecondY) {
                if(!Farm.yLocations.contains(y-1) && !Farm.xLocations.contains(x)) {
                    y--;
                    Energy -= 1/20;
                    isWalk = 1;
                }
            }else if(y < SecondY){
                if(!Farm.yLocations.contains(y+1) && !Farm.xLocations.contains(x)) {
                    y++;
                    Energy += 1/20;
                    isWalk = 1;
                }
            }
            if(Energy==0){
                System.out.println("GHASH KARD");
                x = Farm.xLocations.indexOf(x);
                y = Farm.yLocations.indexOf(y);
            }
            if(isWalk == 1){
                if(x != SecondX && y != SecondY){
                    System.out.println("Can't walk");
                    return false;
                }
            }

        }
        return true;
    }


}
