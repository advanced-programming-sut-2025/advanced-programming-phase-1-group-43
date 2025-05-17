package Model;

public class Tree extends Plant{
    private boolean isChoppable;
    public static int  x;
    public static int  y;
    public Tree(int x, int y,boolean isChoppable) {
        this.x = x;
        this.y = y;
        this.isChoppable = isChoppable;
    }
}
