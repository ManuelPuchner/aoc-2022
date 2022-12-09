package days.day9;

public class Node {
    protected int x = 11;
    protected int y = 5;
    protected int lastX = 0;
    protected int lastY = 0;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    protected Direction lastDirection = null;
}