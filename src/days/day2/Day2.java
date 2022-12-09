package days.day2;

import days.Day;

import java.util.HashMap;

public class Day2 extends Day {
    private final HashMap<Character, Number> pointsMap = new HashMap<>();

    public Day2() {
        super();
        // A,X: Rock
        pointsMap.put('A', 1);
        pointsMap.put('X', 1);
        // B, Y: Paper
        pointsMap.put('B', 2);
        pointsMap.put('Y', 2);
        // C, Z: Scissors
        pointsMap.put('C', 3);
        pointsMap.put('Z', 3);
    }

    @Override
    public Object solvePart1() {
        int pointsSum = 0;
//        input = Arrays.asList("A Y", "B X", "C Z");
        for (String row : input) {
            String[] parts = row.split(" ");
            char opponentMove = parts[0].charAt(0);
            char myMove = parts[1].charAt(0);

            pointsSum += getRoundPoints(opponentMove, myMove);
            pointsSum += pointsMap.get(myMove).intValue();
        }
        return pointsSum;
    }

    private int getRoundPoints(char opponentMove, char myMove) {
        int points = 0;
        if (pointsMap.get(opponentMove).equals(pointsMap.get(myMove))) {
            points += 3;
        } else if (
                (opponentMove == 'A' && myMove == 'Z') ||
                        (opponentMove == 'B' && myMove == 'X') ||
                        (opponentMove == 'C' && myMove == 'Y')
        ) {
            points += 0;
        } else {
            points += 6;
        }
        return points;
    }

    private char getLoosingMove(char move) {
        return switch (move) {
            // Rock -> Scissors
            case 'A' -> 'Z';
            // Paper -> Rock
            case 'B' -> 'X';
            // Scissors -> Paper
            case 'C' -> 'Y';
            default -> ' ';
        };
    }

    private char getWinningMove(char move) {
        return switch (move) {
            // Rock -> Paper
            case 'A' -> 'Y';
            // Paper -> Scissors
            case 'B' -> 'Z';
            // Scissors -> Rock
            case 'C' -> 'X';
            default -> ' ';
        };
    }

    @Override
    public Object solvePart2() {
        int pointsSum = 0;
        for (String row : input) {
            String[] parts = row.split(" ");
            char opponentMove = parts[0].charAt(0);
            char decision = parts[1].charAt(0);
            char myMove;

            // draw
            if (decision == 'Y') {
                myMove = opponentMove;
            }
            // loose
            else if (decision == 'X') {
                myMove = getLoosingMove(opponentMove);
            }
            // win
            else {
                myMove = getWinningMove(opponentMove);
            }

            pointsSum += getRoundPoints(opponentMove, myMove);
            pointsSum += pointsMap.get(myMove).intValue();
        }

        return pointsSum;
    }
}
