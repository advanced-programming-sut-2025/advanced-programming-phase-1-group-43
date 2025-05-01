package service;

import Model.enums.Season;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

public class TimeService {
    private LocalTime time;
    private LocalDate date;
    private final WeatherService weather;

    public TimeService(WeatherService weather) {
        this.weather = weather;
        this.time = LocalTime.of(9,0);
        this.date = LocalDate.of(1,1,1);    // day 1
        // initial forecast for day 1:
        weather.rollForecast(getSeason());
        weather.advanceToNextDay();
        weather.applyEffects();
    }

    public String getSeason() {
        return Season.fromDayOfYear(date.getDayOfYear()).name();
    }

    private void nextDay() {
        date = date.plusDays(1);
        time = LocalTime.of(9,0);

        // determine new season
        Season s = Season.fromDayOfYear(date.getDayOfYear());
        // make that forecast the “current” weather
        weather.advanceToNextDay();
        // roll tomorrow’s forecast based on season
        weather.rollForecast(s.name());
        // apply its effects (rain auto-irrigation, storm lightning, etc.)
        weather.applyEffects();
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
        if (h < 0) throw new IllegalArgumentException("Cannot advance negative hours");

        int fullDays = h / 24;
        int leftoverHours = h % 24;

        // add full days
        for (int i = 0; i < fullDays; i++) {
            nextDay();
        }

        // then add leftover hours
        time = time.plusHours(leftoverHours);
        if (time.isAfter(LocalTime.of(21, 59))) {
            nextDay();
        }
    }

    public void advanceDays(int d) {
        for (int i=0;i<d;i++) nextDay();
    }

    public void cheatAdvanceTime(int h) { advanceHours(h); }
    public void cheatAdvanceDate(int d) { advanceDays(d); }
}
