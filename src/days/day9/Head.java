package days.day9;

public class Head extends Node{
    public void move(Direction direction) {
        switch (direction) {
            case UP -> {
                lastY = y;
                lastX = x;
                lastDirection = Direction.UP;
                y++;
            }
            case DOWN -> {
                lastY = y;
                lastX = x;
                lastDirection = Direction.DOWN;
                y--;
            }
            case LEFT -> {
                lastX = x;
                lastY = y;
                lastDirection = Direction.LEFT;
                x--;
            }
            case RIGHT -> {
                lastX = x;
                lastY = y;
                lastDirection = Direction.RIGHT;
                x++;
            }
        }
    }
}