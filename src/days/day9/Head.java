package days.day9;

public class Head extends Node {
    public void move(Direction direction) {
        lastY = y;
        lastX = x;
        switch (direction) {
            case UP -> {
                lastDirection = Direction.UP;
                y++;
            }
            case DOWN -> {
                lastDirection = Direction.DOWN;
                y--;
            }
            case LEFT -> {
                lastDirection = Direction.LEFT;
                x--;
            }
            case RIGHT -> {
                lastDirection = Direction.RIGHT;
                x++;
            }
        }
    }
}