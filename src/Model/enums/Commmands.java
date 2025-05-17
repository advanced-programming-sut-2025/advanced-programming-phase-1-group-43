package Model.enums;

public enum Commmands {
    CraftInf("craftinfo\\s+-n\\s+(?<CraftName>\\S+)"),
    showPlantInf("showplant\\s+-l\\s+<(?<X>\\d+),\\s*(?<Y>\\d+)>"),
    Fertilize("fertilize\\s+-f\\s+(?<Fertilizer>\\S+)\\s+-d\\s+(?<Direction>\\S+)"),
    HowMuchWater("howmuch water"),
    Watering("watering\\s+(?<X>\\d+)\\s+(?<Y>\\d+)"),
    Harvest("harvest\\s+(?<X>\\d+)\\s+(?<Y>\\d+)"),
    Walk("walk\\s+-l\\s+(?<X>\\d+)\\s+(?<Y>\\d+)"),
    PrintMap("print\\s+map\\s+-l\\s+(?<X>\\d+)\\s+(?<Y>\\d+)\\s+-s\\s+(?<Size>\\d+)")
    ;
    private final String Pattern;
    Commmands (String Pattern) {
        this.Pattern = Pattern;
    }
    public String getPattern () {
        return Pattern;
    }
}
