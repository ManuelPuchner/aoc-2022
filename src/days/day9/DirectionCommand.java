package days.day9;

public record DirectionCommand(Direction direction, int amount) {
    @Override
    public String toString() {
        return direction + " " + amount;
    }
}
