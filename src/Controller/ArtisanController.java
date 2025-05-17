package Controller;

import Model.ArtisanMachine;
import Model.User;
import Model.Inventory;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

/**
 * Controller to handle artisan commands within game context.
 */
public class ArtisanController {
    private final Map<String, ArtisanMachine> machines = new HashMap<>();
    private final User user;

    public ArtisanController(User user) {
        this.user = user;
        // initialize all machines per spreadsheet
        machines.put("Bee_House", new ArtisanMachine("Bee_House", Duration.of(4, ChronoUnit.DAYS), true));
        machines.put("Loom", new ArtisanMachine("Loom", Duration.of(4, ChronoUnit.HOURS), false));
        machines.put("Cheese_Press", new ArtisanMachine("Cheese_Press", Duration.of(3, ChronoUnit.HOURS), false));
        machines.put("Mayonnaise_Machine", new ArtisanMachine("Mayonnaise_Machine", Duration.of(3, ChronoUnit.HOURS), false));
        machines.put("Oil_Maker", new ArtisanMachine("Oil_Maker", Duration.of(6, ChronoUnit.HOURS), false));
        machines.put("Fish_Smoker", new ArtisanMachine("Fish_Smoker", Duration.of(1, ChronoUnit.HOURS), false));
        machines.put("Keg", new ArtisanMachine("Keg", Duration.of(1, ChronoUnit.DAYS), false));
        machines.put("Preserves_Jar", new ArtisanMachine("Preserves_Jar", Duration.of(6, ChronoUnit.HOURS), false));
        machines.put("Dehydrator", new ArtisanMachine("Dehydrator", Duration.of(1, ChronoUnit.DAYS), false));
        machines.put("Charcoal_Kiln", new ArtisanMachine("Charcoal_Kiln", Duration.of(1, ChronoUnit.HOURS), false));
        machines.put("Furnace", new ArtisanMachine("Furnace", Duration.of(4, ChronoUnit.HOURS), false));
    }

    /**
     * Use a machine: either enqueue input or get output.
     * @param args [use|get, machineName, [item]]
     */
    public String handle(String[] args) {
        if (args.length < 2) return "Usage: artisan <use|get> <machine_name> [item]";
        String cmd = args[0], name = args[1];
        if (!machines.containsKey(name)) return "Unknown artisan machine: " + name;
        ArtisanMachine m = machines.get(name);
        // check adjacency via farm
        if (!user.getFarm().isAdjacentToMachine(name))
            return "You are not next to " + name;
        Inventory inv = user.getFarm().getInventory();
        switch (cmd) {
            case "use":
                if (m.isAuto()) return "Cannot use "+name+" manually.";
                if (args.length<3) return "Usage: artisan use <machine> <item>";
                String item = args[2];
                if (!m.isValidInput(item)) return "Cannot process '"+item+"' in "+name;
                if (!inv.hasItem(item,1)) return "You don't have '"+item+"'";
                inv.removeItem(item,1);
                return m.use(item);
            case "get":
                if (!m.hasTask()) return "No task in "+name;
                String out = m.get();
                inv.addItem(out,1);
                return out;
            default:
                return "Unknown artisan command: " + cmd;
        }
    }
}