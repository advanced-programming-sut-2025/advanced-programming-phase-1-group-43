package Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Farmm {
    // Fixed Elements
    Cottage cottage = new Cottage(20,20,40,40);
    Greenhouse greenHouse = new Greenhouse(20,10,40,40);
    StoneMining stoneMining = new StoneMining(20,20,40,40);
    Lake lake = new Lake(50,30,80,40);

    // Random Elements
    ArrayList<Tree> trees = new ArrayList<>();
    ArrayList<Stone> stones = new ArrayList<>();
    Random random = new Random();

    public static ArrayList<Integer> xLocations = new ArrayList<>(Arrays.asList(40,50,40,50));
    public static ArrayList<Integer> yLocations = new ArrayList<>(Arrays.asList(60, 10, 15, 17));


    public Farmm() {
        for (int i = 0; i < random.nextInt(20) + 20; i++) {
            int x = random.nextInt(100);
            int y = random.nextInt(100);
            while (xLocations.contains(x) && yLocations.contains(y)) {
                x = random.nextInt(100);
                y = random.nextInt(100);
            }
            xLocations.add(x);
            yLocations.add(y);
            trees.add(new Tree("none", x, y, random.nextBoolean()));
        }

        for (int i = 0; i < random.nextInt(20) + 20; i++) {
            int x = random.nextInt(100);
            int y = random.nextInt(100);
            while (xLocations.contains(x) && yLocations.contains(y)) {
                x = random.nextInt(100);
                y = random.nextInt(100);
            }
            xLocations.add(x);
            yLocations.add(y);
            stones.add(new Stone(x, y));
        }
    }
}
