package Controller;

import service.TimeService;

public class TimeController {
    private final TimeService time;

    public TimeController(TimeService time) { this.time = time; }

    public void handle(String cmd) {
        if (cmd.equals("time")) System.out.println(time.getTime());
        else if (cmd.equals("date")) System.out.println(time.getDate());
        else if (cmd.equals("datetime")) System.out.println(time.getDateTime());
        else if (cmd.equals("day of the week")) System.out.println(time.getDayOfWeek());
        else if (cmd.startsWith("cheat advance time ")) {
            int h = Integer.parseInt(cmd.split(" ")[3].replace("h",""));
            time.cheatAdvanceTime(h);
        }
        else if (cmd.startsWith("cheat advance date ")) {
            int d = Integer.parseInt(cmd.split(" ")[3].replace("d",""));
            time.cheatAdvanceDate(d);
        }
        else System.out.println("Invalid time command");
    }
}
