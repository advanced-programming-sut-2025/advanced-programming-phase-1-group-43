package Model;

import Model.Crops.Trees.All;

public class Tree extends All {
    private boolean isChoppable;
    public static int  x;
    public static int  y;
    public static String TreeType;
    public boolean isAttacked = false;
    public Tree(String Type,int x, int y,boolean isChoppable) {
        this.TreeType = Type;
        this.x = x;
        this.y = y;
        this.isChoppable = isChoppable;
    }
}
