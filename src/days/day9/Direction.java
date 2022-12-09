package days.day9;

public enum Direction {
    LEFT,
    RIGHT,
    UP,
    DOWN;

    public static Direction getFromString(String s) {
        return switch (s) {
            case "L" -> LEFT;
            case "R" -> RIGHT;
            case "U" -> UP;
            case "D" -> DOWN;
            default -> throw new IllegalArgumentException("Invalid direction: " + s);
        };
    }
}
