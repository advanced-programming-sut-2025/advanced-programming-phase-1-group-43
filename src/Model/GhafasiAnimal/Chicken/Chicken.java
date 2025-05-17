package Model.GhafasiAnimal.Chicken;

import java.util.ArrayList;
import java.util.Objects;

public class Chicken {
    public final static int Price = 800;
    public static int love = 0 ;
    public SmallEgg smallEgg = new SmallEgg();
    public BigEgg bigEgg = new BigEgg();
    public ArrayList<Object> Products = new ArrayList<>();

    public Chicken() {
        Products.add(smallEgg);
        Products.add(bigEgg);
    }
}
