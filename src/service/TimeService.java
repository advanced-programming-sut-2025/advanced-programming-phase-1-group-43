package service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

public class TimeService {
    private LocalTime time;
    private LocalDate date;

    public TimeService() {
        time = LocalTime.of(9,0);
        date = LocalDate.of(1,1,1); // day1 spring
    }

    public LocalTime getTime() { return time; }
    public LocalDate getDate() { return date; }

    public String getDateTime() {
        return date + " " + time;
    }

    public DayOfWeek getDayOfWeek() {
        return date.getDayOfWeek();
    }

    public void advanceHours(int h) {
        time = time.plusHours(h);
        if (time.isAfter(LocalTime.of(21,59))) {
            nextDay();
        }
    }

    public void advanceDays(int d) {
        for (int i=0;i<d;i++) nextDay();
    }

    private void nextDay() {
        date = date.plusDays(1);
        time = LocalTime.of(9,0);
    }

    public void cheatAdvanceTime(int h) { advanceHours(h); }
    public void cheatAdvanceDate(int d) { advanceDays(d); }
}
