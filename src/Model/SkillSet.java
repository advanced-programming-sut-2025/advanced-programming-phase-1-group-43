package Model;

public class SkillSet {
    private int farming;
    private int mining;
    private int fishing;
    private int foraging;
    private int animalHandling;

    public SkillSet(int mining, int farming, int fishing, int foraging, int animalHandling) {
        this.mining = mining;
        this.farming = farming;
        this.fishing = fishing;
        this.foraging = foraging;
        this.animalHandling = animalHandling;
    }
}
