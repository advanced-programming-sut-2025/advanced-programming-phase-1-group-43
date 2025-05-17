package Model.GhafasiAnimal.Duck;

import java.util.ArrayList;
import java.util.Objects;

public class Duck {
    public final static int Price = 1200;
    public static int love = 0 ;
    public DuckEgg duckEgg = new DuckEgg();
    public DuckFeather feather = new DuckFeather();
    public ArrayList<Object> Product = new ArrayList<>();
    public Duck() {
        Product.add(duckEgg);
        Product.add(feather);
    }
}
