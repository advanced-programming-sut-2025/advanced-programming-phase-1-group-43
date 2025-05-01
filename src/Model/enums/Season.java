package Model.enums;

public enum Season {
    SPRING, SUMMER, FALL, WINTER;

    public static Season fromDayOfYear(int dayOfYear) {
        int idx = ((dayOfYear - 1) / 28) % 4;
        return values()[idx];
    }
}
