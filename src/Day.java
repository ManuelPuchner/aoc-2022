import util.FileUtils;

import java.util.List;

public abstract class Day {
    List<String> input;
    int day;


    abstract public Object solvePart1();
    abstract public Object solvePart2();

    public void solve() {
        System.out.println("Solution for day " + day);
        System.out.println("\tPart 1: " + solvePart1());
        System.out.println("\tPart 2: " + solvePart2());
    }

    public Day() {
        this.day = Integer.parseInt(getClass().getSimpleName().substring(3));
        input = FileUtils.getInput(this.day, "input");
    }
}
