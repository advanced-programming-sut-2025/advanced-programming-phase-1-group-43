package Model.TavileAnimal.Cow;

import java.util.ArrayList;
import java.util.Objects;

public class Cow {
    public static final int Price = 1500;
    public CowMilk cowMilk = new CowMilk();
    public CowBigMilk cowBigMilk = new CowBigMilk();
    public ArrayList<Object> Products = new ArrayList<>();
    public Cow() {
        Products.add(cowMilk);
        Products.add(cowBigMilk);
    }
}
