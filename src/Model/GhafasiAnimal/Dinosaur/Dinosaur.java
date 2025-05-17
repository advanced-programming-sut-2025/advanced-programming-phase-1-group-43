package Model.GhafasiAnimal.Dinosaur;

import java.util.ArrayList;

public class Dinosaur {
    public final static int Price = 14000;
    public static int love = 0 ;
    public DinosaurEgg egg = new DinosaurEgg();
    public ArrayList<Object> Product = new ArrayList<>();
    public Dinosaur() {
        Product.add(egg);
    }

}
