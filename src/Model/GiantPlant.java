package Model;

public class GiantPlant{
    public int XFirst;
    public int YFirst;
    public int XSecond;
    public int YSecond;
    public String PlantType;
    public GiantPlant(String plantType, int XFirst, int YFirst, int XSecond, int YSecond) {
        this.PlantType = plantType;
        this.XFirst = XFirst;
        this.YFirst = YFirst;
        this.XSecond = XSecond;
        this.YSecond = YSecond;
    }
}