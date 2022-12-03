import java.util.ArrayList;
import java.util.List;

public class Day3 extends Day {

    private int getPriority(char character) {
        if(character >= 'A' && character <= 'Z') {
            return character - 'A' + 1 + 26;
        } else if(character >= 'a' && character <= 'z') {
            return character - 'a' + 1;
        } else {
            return 0;
        }
    }

    @Override
    public Object solvePart1() {
        int sum = 0;

        for(String line : input) {
            List<Character> firstHalf = line.substring(0, line.length() / 2).chars().mapToObj(c -> (char) c).toList();
            List<Character> secondHalf = line.substring(line.length() / 2).chars().mapToObj(c -> (char) c).toList();

            char commonChar = ' ';
            for(char c : firstHalf) {
                if(secondHalf.contains(c)) {
                    commonChar = c;
                }
            }

            int priority = getPriority(commonChar);

            sum += priority;

        }

        return sum;
    }

    @Override
    public Object solvePart2() {
        // partition input list in parts of 3
        List<List<String>> groups = new ArrayList<>();
        for(int i = 0; i < input.size(); i += 3) {
            groups.add(input.subList(i, i + 3));
        }

        int sum = 0;

        for(List<String> group : groups) {
            List<Character> firstHalf = group.get(0).chars().mapToObj(c -> (char) c).toList();
            List<Character> secondHalf = group.get(1).chars().mapToObj(c -> (char) c).toList();
            List<Character> thirdHalf = group.get(2).chars().mapToObj(c -> (char) c).toList();
            char commonChar = ' ';
            for(char c : firstHalf) {
                if(secondHalf.contains(c) && thirdHalf.contains(c)) {
                    commonChar = c;
                }
            }
            sum += getPriority(commonChar);
        }

        return sum;
    }
}
