package Controller;

import Model.User;

import java.util.Random;

public class Fishing {
    public void fish(String AnimalName) {
        Random rand = new Random();
        int numberOfFish = rand.nextInt(1) * 15 * User.FishingSkill;
    }
}
