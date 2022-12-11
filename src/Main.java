import days.day1.Day1;
import days.day2.Day2;
import days.day3.Day3;
import days.day4.Day4;
import days.day5.Day5;
import days.day6.Day6;
import days.day7.Day7;
import days.day8.Day8;
import days.day9.Day9;
import days.day10.Day10;
import days.*;

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
        days.add(new Day7());
        days.add(new Day8());
        days.add(new Day9());
        days.add(new Day10());

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
