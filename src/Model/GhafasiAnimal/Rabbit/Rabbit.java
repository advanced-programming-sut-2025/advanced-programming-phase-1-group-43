package Model.GhafasiAnimal.Rabbit;

import java.util.ArrayList;

public class Rabbit {
    public final static int Price = 8000;
    public static int love = 0 ;
    public RabbitFeet feet = new RabbitFeet();
    public RabbitFeather feather = new RabbitFeather();
    public ArrayList<Object> Product = new ArrayList<>();
    public Rabbit() {
        Product.add(feather);
        Product.add(feet);
    }
}
