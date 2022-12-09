package days.day9;

import days.Day;

import java.util.*;

public class Day9 extends Day {

    public Day9() {
        //input = FileUtils.readAllLines("input/9/example");
    }

    int width = 0;
    int height = 0;



    @Override
    public Object solvePart1() {
        List<DirectionCommand> directions = input.stream().map(s -> {
            String[] parts = s.split(" ");
            return new DirectionCommand(Direction.getFromString(parts[0]), Integer.parseInt(parts[1]));
        }).toList();

        HashSet<Coordinate> visitedCoordinates = new HashSet<>();

        Head head = new Head();
        Tail tail = new Tail(head);

        for(DirectionCommand direction : directions) {
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
        return null;
    }
}
