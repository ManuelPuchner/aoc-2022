package days.day9;

import days.Day;
import days.day9.util.Day9Util;
import util.FileUtils;

import java.util.*;

public class Day9 extends Day {

    private List<DirectionCommand> directionCommands;
    public Day9() {
        //input = FileUtils.readAllLines("input/9/example2");

        directionCommands = input.stream().map(s -> {
            String[] parts = s.split(" ");
            return new DirectionCommand(Direction.getFromString(parts[0]), Integer.parseInt(parts[1]));
        }).toList();
    }



    @Override
    public Object solvePart1() {
        HashSet<Coordinate> visitedCoordinates = new HashSet<>();

        Head head = new Head();
        Tail tail = new Tail(head, 1);

        for (DirectionCommand direction : directionCommands) {
            for (int i = 0; i < direction.amount(); i++) {
                head.move(direction.direction());
                tail.follow(direction.direction());
                visitedCoordinates.add(new Coordinate(tail.x, tail.y));
            }
        }

        return visitedCoordinates.size();
    }


    @Override
    public Object solvePart2() {
        HashSet<Coordinate> visitedCoordinates = new HashSet<>();

        Head head = new Head();
        List<Tail> tails = new ArrayList<>();
        Tail lastTail = new Tail(head, 1);
        tails.add(lastTail);
        for (int i = 0; i < 8; i++) {
            Tail newTail = new Tail(lastTail, i+2);
            tails.add(newTail);
            lastTail = newTail;
        }

        System.out.println("Initial state:");

        int width = "..........................".length();
        int height = 20;

        Day9Util.printGrid(width, height, head, tails);

        for (DirectionCommand direction : directionCommands) {
            for (int i = 0; i < direction.amount(); i++) {
//                System.out.println("Moving head " + direction.direction());
                head.move(direction.direction());
//                Day9Util.printGrid(width, height, head, tails);
                for (Tail tail : tails) {
                    tail.follow(direction.direction());
//                    System.out.println("Moving tail " + tail.getTailNumber() + " " + direction.direction());
//                    Day9Util.printGrid(width, height, head, tails);
                    if(tail.getTailNumber() == 9) {
                        visitedCoordinates.add(new Coordinate(tail.x, tail.y));
                    }
                }
//                Day9Util.printGrid(width, height, head, tails);
            }
//            Day9Util.printGrid(width, height, head, tails);
        }

        return visitedCoordinates.size();
    }
}