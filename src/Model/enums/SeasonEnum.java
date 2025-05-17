package Model.enums;

public enum SeasonEnum {
    SPRING, SUMMER, FALL, WINTER;

    public static SeasonEnum fromDayOfYear(int dayOfYear) {
        int idx = ((dayOfYear - 1) / 28) % 4;
        return values()[idx];
    }
}
