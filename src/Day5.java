import util.FileUtils;

import java.util.*;

public class Day5 extends Day {

    private int getStackNumbersIndex() {
        int numbersIndex = 0;
        for (String line : input) {
            if (line.length() > 0) {
                numbersIndex++;
            } else {
                break;
            }
        }
        return numbersIndex-1;
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

            for(int i = numbersIndex-1; i >= 0; i--) {
                if(input.get(i).length() > index && input.get(i).charAt(index) != ' ') {
                    stack.push(input.get(i).charAt(index));
                }
            }

            stackList.add(stack);
        }
        return stackList;
    }

    @Override
    public Object solvePart1() {
        //input = FileUtils.readAllLines("input/5/example");
        List<Stack<Character>> stacks = getStacks();
        List<String> commands = input.subList(getStackNumbersIndex()+2, input.size());


        for(String command : commands) {
            String[] commandParts = command.split("\s+");
            int amount = Integer.parseInt(commandParts[1]);
            int fromIndex = Integer.parseInt(commandParts[3])-1;
            int toIndex = Integer.parseInt(commandParts[5])-1;

            for (int i = 0; i < amount; i++) {
                stacks.get(toIndex).push(stacks.get(fromIndex).pop());
            }
        }

        return getTopOfStacks(stacks);
    }

    private String getTopOfStacks(List<Stack<Character>> stacks) {
        StringBuilder result = new StringBuilder();
        for (Stack<Character> stack : stacks) {
            result.append(stack.peek());
        }
        return result.toString();
    }

    @Override
    public Object solvePart2() {
        List<Stack<Character>> stacks = getStacks();
        List<String> commands = input.subList(getStackNumbersIndex()+2, input.size());

        for(String command : commands) {
            String[] commandParts = command.split("\s+");
            int amount = Integer.parseInt(commandParts[1]);
            int fromIndex = Integer.parseInt(commandParts[3]) - 1;
            int toIndex = Integer.parseInt(commandParts[5]) - 1;


            Stack<Character> tempStack = new Stack<>();
            for (int i = 0; i < amount; i++) {
                tempStack.push(stacks.get(fromIndex).pop());
            }
            for (int i = 0; i < amount; i++) {
                stacks.get(toIndex).push(tempStack.pop());
            }
        }

        return getTopOfStacks(stacks);
    }
}
