import java.util.HashMap;
import java.util.Objects;

public class Day2 extends Day {


    private HashMap<String, Number> pointsMap = new HashMap<>();

    public Day2() {
        super();
        // A: Rock
        pointsMap.put("A", 1);
        // B: Paper
        pointsMap.put("B", 2);
        // C: Scissors
        pointsMap.put("C", 3);
        // X: Rock
        pointsMap.put("X", 1);
        // Y: Paper
        pointsMap.put("Y", 2);
        // Z: Scissors
        pointsMap.put("Z", 3);
    }

    @Override
    public Object solvePart1() {
        int pointsSum = 0;
//        input = Arrays.asList("A Y", "B X", "C Z");
        for(String row : input) {
            String[] parts = row.split(" ");
            char opponentMove = parts[0].charAt(0);
            char myMove = parts[1].charAt(0);

            pointsSum += getRoundPoints(opponentMove, myMove);

            pointsSum += pointsMap.get(String.valueOf(myMove)).intValue();
        }
        return pointsSum;
    }

    private int getRoundPoints (char opponentMove, char myMove) {
        int points = 0;
        if(Objects.equals(pointsMap.get(String.valueOf(opponentMove)), pointsMap.get(String.valueOf(myMove)))) {
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
            if(decision == 'Y') {
                myMove = opponentMove;
            }
            // loose
            else if(decision == 'X') {
                myMove = getLoosingMove(opponentMove);
            }
            // win
            else {
                myMove = getWinningMove(opponentMove);
            }

            pointsSum += getRoundPoints(opponentMove, myMove);

            pointsSum += pointsMap.get(String.valueOf(myMove)).intValue();

        }

        return pointsSum;
    }
}
