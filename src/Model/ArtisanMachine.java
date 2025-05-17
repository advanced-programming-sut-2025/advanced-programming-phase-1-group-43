package Model;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class ArtisanMachine {
    private final String name;
    private final Duration time;
    private final boolean auto;
    private final Queue<Task> q = new LinkedList<>();
    private static final Map<String, String> recipes = new HashMap<>();
    private static final Map<String, Boolean> inputAllowed = new HashMap<>();

    static {
        recipes.put("Salmon", "Smoked_Salmon");
        recipes.put("Milk", "Cheese"); recipes.put("Large Milk", "Cheese");
        recipes.put("Goat Milk", "Goat_Cheese"); recipes.put("Large Goat Milk", "Goat_Cheese");
        recipes.put("Egg", "Mayonnaise"); recipes.put("Large Egg", "Mayonnaise"); recipes.put("Duck Egg","Duck_Mayonnaise");
        recipes.put("Dinosaur Egg","Dinosaur_Mayonnaise");
        recipes.put("Wool","Cloth");
        recipes.put("Oil Seeds","Oil");
        recipes.put("Rice","Vinegar");
        recipes.put("Fruit","Wine"); recipes.put("Red Fruit","Wine"); // etc.

        // machines that need input allow all in recipes
        for (String in:recipes.keySet()) inputAllowed.put(in,true);
    }

    public ArtisanMachine(String name, Duration time, boolean auto) {
        this.name=name; this.time=time; this.auto=auto;
    }
    public boolean isAuto(){return auto;}
    public boolean isValidInput(String item){return inputAllowed.getOrDefault(item,false);}
    public boolean hasTask(){return !q.isEmpty();}
    public String use(String in){ // enqueue
        LocalDateTime ready=LocalDateTime.now().plus(time);
        q.add(new Task(in, ready));
        return String.format("Task queued: %s on %s ready at %s", in, name, ready);
    }
    public String get(){
        Task t=q.peek();
        if (LocalDateTime.now().isBefore(t.ready))
            return String.format("%s not ready until %s", t.in, t.ready);
        t=q.poll();
        String out = auto? recipes.getOrDefault(name, name+"_prod"): recipes.get(t.in);
        return out;
    }
    private static class Task{String in; LocalDateTime ready; Task(String i, LocalDateTime r){in=i;ready=r;}}
}