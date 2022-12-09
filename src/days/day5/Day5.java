package days.day5;

import days.Day;

import java.util.*;

public class Day5 extends Day {
    List<String> commands;

    public Day5() {
        super();
//        input = FileUtils.readAllLines("input/5/example");
        commands = input.subList(getStackNumbersIndex() + 2, input.size());
    }

    @Override
    public Object solvePart1() {
        List<Stack<Character>> stacks = getStacks();
        for (String command : commands) {
            HashMap<String, Integer> commandInput = getCommandInput(command);
            for (int i = 0; i < commandInput.get("amount"); i++) {
                stacks.get(commandInput.get("toIndex")).push(stacks.get(commandInput.get("fromIndex")).pop());
            }
        }
        return getTopOfStacks(stacks);
    }

    @Override
    public Object solvePart2() {
        List<Stack<Character>> stacks = getStacks();

        for (String command : commands) {
            HashMap<String, Integer> commandInput = getCommandInput(command);
            Stack<Character> tempStack = new Stack<>();
            for (int i = 0; i < commandInput.get("amount"); i++) {
                tempStack.push(stacks.get(commandInput.get("fromIndex")).pop());
            }
            for (int i = 0; i < commandInput.get("amount"); i++) {
                stacks.get(commandInput.get("toIndex")).push(tempStack.pop());
            }
        }

        return getTopOfStacks(stacks);
    }

    private HashMap<String, Integer> getCommandInput(String command) {
        String[] commandParts = command.split("\s+");
        HashMap<String, Integer> commandInput = new HashMap<>();
        commandInput.put("amount", Integer.parseInt(commandParts[1]));
        commandInput.put("fromIndex", Integer.parseInt(commandParts[3]) - 1);
        commandInput.put("toIndex", Integer.parseInt(commandParts[5]) - 1);
        return commandInput;
    }

    private String getTopOfStacks(List<Stack<Character>> stacks) {
        StringBuilder result = new StringBuilder();
        for (Stack<Character> stack : stacks) {
            result.append(stack.peek());
        }
        return result.toString();
    }

    private int getStackNumbersIndex() {
        int numbersIndex = 0;
        for (String line : input) {
            if (line.length() > 0) {
                numbersIndex++;
            } else {
                break;
            }
        }
        return numbersIndex - 1;
    }

    private List<Stack<Character>> getStacks() {
        int numbersIndex = getStackNumbersIndex();
        List<Integer> stackNumbers = Arrays.stream(input.get(numbersIndex).split("\s+")).filter(elem -> elem.length() > 0).map(Integer::parseInt).toList();
        int numberOfStacks = stackNumbers.stream().max(Integer::compareTo).orElse(0);
        List<Stack<Character>> stackList = new ArrayList<>();
        List<Integer> stackIndexList = new ArrayList<>();

        for (int i = 0; i < numberOfStacks; i++) {
            List<Character> stackIndices = input.get(numbersIndex).chars().mapToObj(c -> (char) c).toList();
            stackIndexList.add(stackIndices.indexOf((char) (i + 49)));
        }

        for (int index : stackIndexList) {
            Stack<Character> stack = new Stack<>();
            for (int i = numbersIndex - 1; i >= 0; i--) {
                if (input.get(i).length() > index && input.get(i).charAt(index) != ' ') {
                    stack.push(input.get(i).charAt(index));
                }
            }
            stackList.add(stack);
        }
        return stackList;
    }
}
