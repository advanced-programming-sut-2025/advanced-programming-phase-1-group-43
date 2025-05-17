package Model.TavileAnimal.Sheep;

import java.util.ArrayList;

public class Sheep {
    public static final int Price = 8000;
    public SheepFeather sheep = new SheepFeather();
    public ArrayList<Object> Products = new ArrayList<>();
    public Sheep() {
        Products.add(sheep);
    }
}

