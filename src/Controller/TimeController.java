package Controller;

import service.TimeService;

public class TimeController {
    private final TimeService times;

    public TimeController(TimeService times) {
        this.times = times;
    }

    public void handle(String input) {
        try {
            if (input.equals("time")) {
                System.out.println("Time: " + times.getTime());
            } else if (input.equals("date")) {
                System.out.println("Date: " + times.getDate());
            } else if (input.equals("datetime")) {
                System.out.println("DateTime: " + times.getDateTime());
            } else if (input.equals("day of the week")) {
                System.out.println("Day of week: " + times.getDayOfWeek());
            } else if (input.startsWith("cheat advance time")) {
                int h = Integer.parseInt(input.split("\\s+")[3].replace("h",""));
                if (h<0) throw new IllegalArgumentException("Must be non-negative");
                times.cheatAdvanceTime(h);
                System.out.println("Time advanced by " + h + "h → " + times.getTime());
            } else if (input.startsWith("cheat advance date")) {
                int d = Integer.parseInt(input.split("\\s+")[3].replace("d",""));
                if (d<0) throw new IllegalArgumentException("Must be non-negative");
                times.cheatAdvanceDate(d);
                System.out.println("Date advanced by " + d + "d → " + times.getDate());
            } else {
                System.out.println("Unknown time command");
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
}
