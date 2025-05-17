package Model;

public class Tree{
    private boolean isChoppable;
    public static int  x;
    public static int  y;
    public Tree(String type, int x, int y,boolean isChoppable) {
        this.x = x;
        this.y = y;
        this.isChoppable = isChoppable;
    }
}
