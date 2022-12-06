import util.FileUtils;

public class Day6 extends Day {
    @Override
    public Object solvePart1() {
        return solveWithCharsCount(4);
    }

    @Override
    public Object solvePart2() {
        return solveWithCharsCount(14);
    }

    public boolean hasOnlyUniqueChars(String str) {
        boolean[] charSet = new boolean[256];
        for (int i = 0; i < str.length(); i++) {
            int val = str.charAt(i);
            if (charSet[val]) {
                return false;
            }
            charSet[val] = true;
        }
        return true;
    }

    private Object solveWithCharsCount(int charsCount) {
        String inputString = input.get(0);
        int indexCount = 0;
        for(int i = 0; i < inputString.length(); i++) {
            String subString = inputString.substring(i, i+charsCount);
            indexCount++;
            if(hasOnlyUniqueChars(subString)) {
                return indexCount+charsCount-1;
            }
        }
        return indexCount+charsCount-1;
    }
}
