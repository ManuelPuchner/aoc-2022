import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final List<Day> days = new ArrayList<>();

    public static void main(String[] args) {
        days.add(new Day1());
        days.add(new Day2());
        days.add(new Day3());
        days.add(new Day4());
        days.add(new Day5());
        days.add(new Day6());


        if(args.length == 1) {
            int day = Integer.parseInt(args[0]);
            solveDay(day);
        } else {
            solveAllDays();
        }
    }

    public static void solveAllDays() {
        for (Day day : days) {
            day.solve();
        }
    }

    public static void solveDay(int day) {
        days.get(day - 1).solve();
    }
}
