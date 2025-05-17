package Model.TavileAnimal.Goat;

import java.util.ArrayList;

public class Goat {
    public static final int Price = 4000;
    public GoatBigMilk goatBigMilk = new GoatBigMilk();
    public GoatMilk goatMilk = new GoatMilk();
    public ArrayList<Object> Products = new ArrayList<>();
    public Goat() {
        Products.add(goatBigMilk);
        Products.add(goatMilk);
    }
}
