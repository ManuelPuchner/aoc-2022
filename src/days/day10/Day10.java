package days.day10;

import days.Day;
import util.FileUtils;

import java.util.LinkedList;
import java.util.List;

public class Day10 extends Day {

    public Day10() {
        //input = FileUtils.readAllLines("input/10/example2");
    }

    @Override
    public Object solvePart1() {
        int registerX = 1;

        int cycleCount = 1;
        boolean isArg = false;
        int sum = 0;
        for (int i = 0; i < input.size(); i++) {
            String[] parts = input.get(i).strip().split(" ");
            if(cycleCount%40 == 20) {
                int product = cycleCount*registerX;
                sum+=product;
            }

            if(cycleCount == 220) {
                break;
            }

            if(parts[0].equals("addx")) {
                if(!isArg) {
                    isArg = true;
                    i--;
                } else {
                    registerX += Integer.parseInt(parts[1]);
                    isArg = false;
                }
            }
            cycleCount++;
        }
        return sum;
    }

    @Override
    public Object solvePart2() {
        int registerX = 1;

        int cycleCount = 1;
        boolean isArg = false;
        int sum = 0;
        List<String> rows = new LinkedList<>();
        StringBuilder row = new StringBuilder();
        int tempCounter = cycleCount;
        for (int i = 0; i < input.size(); i++) {
            String[] parts = input.get(i).strip().split(" ");
            if(cycleCount%40 == 1) {
                rows.add(row.toString());
                row = new StringBuilder();
                tempCounter = 1;
            }

            if(tempCounter-1 >= registerX-1 && tempCounter-1 <= registerX+1) {
                row.append(" #");
            } else {
                row.append(" .");
            }

            if(parts[0].equals("addx")) {
                if(!isArg) {
                    isArg = true;
                    i--;
                } else {
                    registerX += Integer.parseInt(parts[1]);
                    isArg = false;
                }
            }
            cycleCount++;
            tempCounter++;
        }

        rows.add(row.toString());

        return rows.stream().reduce((e1, e2) -> e1 + "\n" + e2).orElse("");
    }
}
