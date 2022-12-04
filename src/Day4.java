import java.util.List;

record Range(int min, int max) {
    public boolean contains(Range other) {
        return min <= other.min && max >= other.max;
    }

    public boolean overlaps(Range other) {
        return min <= other.max && max >= other.min;
    }

    @Override
    public String toString() {
        return String.format("%d-%d", min, max);
    }
}

record Pair(Range first, Range second) {
    public Range getFirst() {
        return first;
    }

    public Range getSecond() {
        return second;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "first=" + first +
                ", second=" + second +
                '}';
    }
}

public class Day4 extends Day {
    private List<Pair> pairs;

    @Override
    public Object solvePart1() {
        // input = FileUtils.readAllLines("input/4/example");
        pairs = input.stream().map(line -> {
            String[] split = line.split(",");
            String[] first = split[0].split("-");
            String[] second = split[1].split("-");
            return new Pair(new Range(Integer.parseInt(first[0]), Integer.parseInt(first[1])),
                    new Range(Integer.parseInt(second[0]), Integer.parseInt(second[1])));
        }).toList();

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
