package Model.enums;

public enum Commmands {
    CraftInf("craftinfo\\s+-n\\s+(?<CraftName>\\S+)"),
    showPlantInf("showplant\\s+-l\\s+<(?<X>\\d+),\\s*(?<Y>\\d+)>"),
    Fertilize("fertilize\\s+-f\\s+(?<Fertilizer>\\S+)\\s+-d\\s+(?<Direction>\\S+)"),
    HowMuchWater("howmuch water"),
    Watering("watering\\s+(?<X>\\d+)\\s+(?<Y>\\d+)"),
    Harvest("harvest\\s+(?<X>\\d+)\\s+(?<Y>\\d+)"),
    Walk("walk\\s+-l\\s+(?<X>\\d+)\\s+(?<Y>\\d+)"),
    PrintMap("print\\s+map\\s+-l\\s+(?<X>\\d+)\\s+(?<Y>\\d+)\\s+-s\\s+(?<Size>\\d+)"),
    BuildGhafas("build -a (?<BuildingName>\\S+) -l (?<X>\\d+)(?<Y>\\d+)"),
    BuyAnimal("buy animal -a (?<Animal>\\S+) -n (?<Name>\\S+)"),
    Pet("Pet\\s+-n\\s+(?<AnimalName>\\S+)"),
    Cheat("cheat set friendship -n (?<AnimalName>\\S+) -c (?<Amount>\\d+)"),
    Animal("animals"),
    Shepherd("shepherd animals -n (?<AnimalName>\\S+) -l (?<X>\\d+)(?<Y>\\d+)"),
    Feed("feed hay -n (?<AnimalName>\\S+)"),
    Produce("produces"),
    SellAnimal("sell animal -n\\s+(?<AnimalName>\\S+)"),
    Fishing("fishing -p\\s+(?<AnimalName>\\S+)")
    ;
    private final String Pattern;
    Commmands (String Pattern) {
        this.Pattern = Pattern;
    }
    public String getPattern () {
        return Pattern;
    }
}
