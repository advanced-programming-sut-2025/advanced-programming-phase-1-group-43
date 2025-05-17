package Controller;

import Model.Farm;
import Model.Stone;
import Model.Tree;

public class PrintMap {
    public void printMap(int  x, int y,int size) {
        for(int i = x ;i <=size+x; i++) {
            for(int j = y ;i <= size+y ; j++) {
                int Exist = 0;
                for(Tree tree: Farm.trees){
                    if(tree.x == i && tree.y == j) {
                        System.out.print("T");
                        Exist = 1;
                    }
                }
                for(Stone stone:Farm.stones){
                    if(stone.x == i && stone.y == j) {
                        System.out.print("S");
                        Exist = 1;
                    }
                }
                if(x>=Farm.cottage.FirstX && x<=Farm.cottage.FirstY && y>=Farm.cottage.FirstX && y<=Farm.cottage.FirstY) {
                    System.out.print("C");
                    Exist = 1;
                }
                if(x>=Farm.lake.FirstX && x<=Farm.lake.FirstY && y>=Farm.lake.FirstX && y<=Farm.lake.FirstY) {
                    System.out.print("L");
                    Exist  = 1 ;
                }
                if(x>=Farm.stoneMining.FirstX && x<=Farm.stoneMining.FirstY && y>=Farm.stoneMining.FirstX && y<=Farm.stoneMining.FirstY) {
                    System.out.print("M");
                    Exist = 1;
                }
                if(x>=Farm.greenHouse.FirstX && x<=Farm.greenHouse.FirstY && y>=Farm.greenHouse.FirstX && y<=Farm.greenHouse.FirstY) {
                    System.out.print("C");
                    Exist = 1;
                }
                if(Exist==0) {
                    System.out.print(" ");
                }
            }
        }
    }
}