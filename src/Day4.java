import java.util.List;

record Range(int min, int max) {
    public boolean contains(Range other) {
        return min <= other.min && max >= other.max;
    }

    public boolean overlaps(Range other) {
        return min <= other.max && max >= other.min;
    }
}

record Pair(Range first, Range second) {
    public Range getFirst() {
        return first;
    }

    public Range getSecond() {
        return second;
    }
}

public class Day4 extends Day {
    private List<Pair> pairs;

    public Day4() {
        super();
        // input = FileUtils.readAllLines("input/4/example");
        pairs = input.stream().map(line -> {
            String[] split = line.split(",");
            String[] first = split[0].split("-");
            String[] second = split[1].split("-");
            return new Pair(
                    new Range(Integer.parseInt(first[0]), Integer.parseInt(first[1])),
                    new Range(Integer.parseInt(second[0]), Integer.parseInt(second[1]))
            );
        }).toList();
    }

    @Override
    public Object solvePart1() {
        int count = 0;
        for (Pair pair : pairs) {
            if(pair.getFirst().contains(pair.getSecond()) || pair.getSecond().contains(pair.getFirst())) {
                count++;
            }
        }
        return count;
    }

    @Override
    public Object solvePart2() {
        int count = 0;
        for(Pair pair : pairs) {
            if(pair.getFirst().overlaps(pair.getSecond())) {
                count++;
            }
        }
        return count;
    }
}
