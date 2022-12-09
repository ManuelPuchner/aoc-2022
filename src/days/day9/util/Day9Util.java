package days.day9.util;

import days.day9.Head;
import days.day9.Tail;

import java.util.List;

public class Day9Util {
    public void printGrid(int width, int height, Head head, List<Tail> tails) {
        for (int i = height; i >= 0; i--) {
            for (int j = 0; j < width; j++) {
                if(head.getX() == j && head.getY() == i) {
                    System.out.print(" H ");
                } else {
                    boolean isTail = false;
                    for (Tail tail : tails) {
                        if(tail.getY() == j && tail.getY() == i) {
                            System.out.print(" T ");
                            isTail = true;
                            break;
                        }
                    }
                    if(!isTail) {
                        System.out.print(" . ");
                    }
                }
            }
            System.out.println();
        }
    }
}
